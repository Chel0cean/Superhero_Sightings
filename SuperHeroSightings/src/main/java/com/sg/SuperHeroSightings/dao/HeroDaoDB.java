package com.sg.SuperHeroSightings.dao;

import com.sg.SuperHeroSightings.dao.SuperpowerDaoDB.SuperpowerMapper;
import com.sg.SuperHeroSightings.dto.Hero;
import com.sg.SuperHeroSightings.dto.OrgStub;
import com.sg.SuperHeroSightings.dto.Organization;
import com.sg.SuperHeroSightings.dto.Superpower;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
        
        return hero;
    }

    @Override
    public Hero getHeroById(int id) {

        try {
            final String GET_HERO_BY_ID = "SELECT * FROM Hero WHERE idHero = ?";
            Hero hero = jdbc.queryForObject(GET_HERO_BY_ID, new HeroMapper(), id);
            
            associateSuperpowerWithHero(hero);
           
            
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
            associateSuperpowerWithHero(hero);
           
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
            associateSuperpowerWithHero(hero);
        }
        return heroes;
    }
     @Override
        public List<Hero> getHeroesByOrganization(Organization organization) {
        
        final String GET_HEROES_BY_ORGANIZATION
                = "SELECT h.idHero, h.name, h.description, h.Superpower_idSuperpower FROM Hero h "
                + " JOIN HeroOrganization ho ON ho.Hero_idHero=h.idHero"
                + " WHERE ho.Organization_idOrganization = ?";

        List<Hero> heroes = jdbc.query(GET_HEROES_BY_ORGANIZATION,
                new HeroMapper(), organization.getOrganizationId());

        for (Hero hero : heroes) {
            associateSuperpowerWithHero(hero);
        }
        return heroes;
    }
      
 

    @Override
    public void insertHeroOrganization(Hero hero, List<Integer> organizationsIds) {
        final String INSERT_HERO_ORGANIZATION = "INSERT INTO "
                + "HeroOrganization(Hero_idHero, Organization_idOrganization) VALUES(?,?)";
        try {
            for (Integer i : organizationsIds) {
                jdbc.update(INSERT_HERO_ORGANIZATION,
                        hero.getHeroId(),
                       i);
            }
        } catch (NullPointerException ex) {

        }
    }

    private void associateSuperpowerWithHero(Hero hero) {

        final String SELECT_SUPERPOWER_FOR_HERO = "SELECT s.idSuperpower, s.name FROM Superpower s "
                + "JOIN Hero h ON h.Superpower_idSuperpower = s.idSuperpower WHERE h.idHero =?";
        Superpower thisPower = jdbc.queryForObject(SELECT_SUPERPOWER_FOR_HERO, new SuperpowerMapper(), hero.getHeroId());

        hero.setSuperPower(thisPower);

    }
     @Override
     public void associateOrgsForHero(Hero hero, List<Organization> organizations) {
        List <OrgStub> orgStubs = new ArrayList();
        for(Organization organization:organizations){
           OrgStub orgstub =new OrgStub(); 
           orgstub.setOrganizationId(organization.getOrganizationId());
           orgstub.setOrganizationName(organization.getOrganizationName());
           orgStubs.add(orgstub);
        }
        

       hero.setOrganizations(orgStubs);

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
