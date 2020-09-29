package com.sg.SuperHeroSightings.dao;

import com.sg.SuperHeroSightings.dao.LocationDaoDB.LocationMapper;
import com.sg.SuperHeroSightings.dao.OrganizationDaoDB.OrganizationMapper;
import com.sg.SuperHeroSightings.dao.SuperpowerDaoDB.SuperpowerMapper;
import com.sg.SuperHeroSightings.dto.Hero;
import com.sg.SuperHeroSightings.dto.Location;
import com.sg.SuperHeroSightings.dto.Organization;
import com.sg.SuperHeroSightings.dto.Superpower;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Chelsea, Karma, Mohammed, Patrick
 */
@Repository
@Profile("database")
public class HeroDaoDB implements HeroDao {

    @Autowired
    JdbcTemplate jdbc;

    //CRUD methods
    @Override
    @Transactional
    public Hero addHero(Hero hero) {
        final String INSERT_HERO = "INSERT INTO Hero(name, description, Superpower_idSuperpower) "
                + "VALUES(?,?,?)";
        jdbc.update(INSERT_HERO,
                hero.getHeroName(),
                hero.getHeroDescription(),
                hero.getSuperPower().getSuperPowerId());

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        hero.setHeroId(newId);
        insertHeroOrganization(hero);
        return hero;
    }

    @Override
    public Hero getHeroById(int id) {

        try {
            final String GET_HERO_BY_ID = "SELECT * FROM Hero WHERE idHero = ?";
            Hero hero = jdbc.queryForObject(GET_HERO_BY_ID, new HeroMapper(), id);
            
            associateSuperpowerandOrganizationsWithHero(hero);
            
            return hero;
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Hero> getAllHeroes() {
        final String GET_ALL_HEROES = "SELECT * FROM Hero";
        List<Hero> heroes = jdbc.query(GET_ALL_HEROES, new HeroMapper());
        
        for (Hero hero : heroes) {
            associateSuperpowerandOrganizationsWithHero(hero);
        }
        return heroes;
    }

    @Override
    public void updateHero(Hero hero) {
        final String UPDATE_HERO = "UPDATE hero SET name = ?, description = ?, "
                + "Superpower_idSuperpower = ? WHERE idHero = ?";
        jdbc.update(UPDATE_HERO,
                hero.getHeroName(),
                hero.getHeroDescription(),
                hero.getSuperPower().getSuperPowerId(),
                hero.getHeroId());

        final String DELETE_HERO_ORGANIZATION = "DELETE FROM HeroOrganization WHERE Hero_idHero = ?";
        jdbc.update(DELETE_HERO_ORGANIZATION, hero.getHeroId());
        
        insertHeroOrganization(hero);
    }

    @Override
    @Transactional
    public void deleteHeroById(int id) {
        final String DELETE_HERO_SIGHTING = "DELETE FROM Sighting WHERE Hero_idHero = ?";
        jdbc.update(DELETE_HERO_SIGHTING, id);

        final String DELETE_HERO_From_Organization = "DELETE FROM HeroOrganization WHERE Hero_idHero = ?";
        jdbc.update(DELETE_HERO_From_Organization, id);

        final String DELETE_HERO = "DELETE FROM Hero WHERE idHero = ?";
        jdbc.update(DELETE_HERO, id);
    }
    
    
    

    //Helper methods
    @Override
    public List<Hero> getHeroesBySuperpower(Superpower superPower) {
        
        final String GET_HEROES_BY_SUPERPOWER
                = "SELECT * FROM Hero h "
                + "WHERE h.Superpower_idSuperpower = ?";

        List<Hero> heroes = jdbc.query(GET_HEROES_BY_SUPERPOWER,
                new HeroMapper(), superPower.getSuperPowerId());

        for (Hero hero : heroes) {
            associateSuperpowerandOrganizationsWithHero(hero);
        }
        return heroes;
    }

    private void insertHeroOrganization(Hero hero) {
        final String INSERT_HERO_ORGANIZATION = "INSERT INTO "
                + "HeroOrganization(Hero_idHero, Organization_idOrganization) VALUES(?,?)";
        try {
            for (Organization organization : hero.getOrganizations()) {
                jdbc.update(INSERT_HERO_ORGANIZATION,
                        hero.getHeroId(),
                        organization.getOrganizationId());
            }
        } catch (NullPointerException ex) {

        }
    }

    private void associateSuperpowerandOrganizationsWithHero(Hero hero) {

        final String SELECT_SUPERPOWER_FOR_HERO = "SELECT s.idSuperpower, s.name FROM Superpower s "
                + "JOIN Hero h ON h.Superpower_idSuperpower = s.idSuperpower WHERE h.idHero =?";
        Superpower thisPower = jdbc.queryForObject(SELECT_SUPERPOWER_FOR_HERO, new SuperpowerMapper(), hero.getHeroId());

        hero.setSuperPower(thisPower);

        final String GET_ORGANIZATION_BY_HEROES
                = "SELECT 'idOrganization','name','description','Location_idLocation', 'contactEmail', 'contactPhone' "
                + "FROM Organization o JOIN HeroOrganization ho ON ho.Organization_idOrganization = o.idOrganization "
                + "WHERE ho.Hero_idHero = ?";
        List<Organization> organizations = jdbc.query(GET_ORGANIZATION_BY_HEROES,
                new OrganizationMapper(), hero.getHeroId());

        if (organizations.isEmpty()) {
            organizations = null;
        } else {
            for (Organization organization : organizations) {
                final String SELECT_LOCATION_FOR_ORGANIZATION = "SELECT l.idLocation, l.name, l.description, l.address, l.city, l.state, l.country, l.zipcode, l.latitude, l.longitude FROM Location l "
                        + "JOIN Organization o ON o.Location_idLocation = l.idLocation WHERE o.idOrganization =?";
                Location thisLocation = jdbc.queryForObject(SELECT_LOCATION_FOR_ORGANIZATION, new LocationMapper(), organization.getOrganizationId());
                organization.setLocation(thisLocation);
            }
        }
        hero.setOrganizations(organizations);
    }

    public static final class HeroMapper implements RowMapper<Hero> {

        @Override
        public Hero mapRow(ResultSet rs, int index) throws SQLException {
            Hero hero = new Hero(rs.getInt("idHero"));
            hero.setHeroName(rs.getString("name"));
            hero.setHeroDescription(rs.getString("description"));

            return hero;
        }
    }

}
