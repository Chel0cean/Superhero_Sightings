package com.sg.SuperHeroSightings.dao;

import com.sg.SuperHeroSightings.dao.HeroDaoDB.HeroMapper;
import com.sg.SuperHeroSightings.dao.LocationDaoDB.LocationMapper;
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
public class OrganizationDaoDB implements OrganizationDao {

    @Autowired
    JdbcTemplate jdbc;

    //CRUD methods
    @Override
    @Transactional
    public Organization addOrganization(Organization organization) {
        final String INSERT_ORGANIZATION = "INSERT INTO Organization(name, description, Location_idLocation, contactEmail, contactPhone) "
                + "VALUES(?,?,?,?,?)";
        jdbc.update(INSERT_ORGANIZATION,
                organization.getOrganizationName(),
                organization.getOrganizationDescription(),
                organization.getLocation().getLocationId(),
                organization.getOrganizationEmail(),
                organization.getOrganizationPhone());

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        organization.setOrganizationId(newId);
        insertOrganizationHero(organization);
        return organization;
    }

    @Override
    public Organization getOrganizationById(int id) {

        final String GET_ORGANIZATION_BY_ID = "SELECT * FROM Organization WHERE idOrganization = ?";

        try {
            Organization organization = jdbc.queryForObject(GET_ORGANIZATION_BY_ID, new OrganizationMapper(), id);
            associateLocationandHeroesWithOrganization(organization);
            return organization;
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Organization> getAllOrganizations() {
        final String GET_ALL_ORGANIZATIONS = "SELECT * FROM Organization";
        List<Organization> organizations = jdbc.query(GET_ALL_ORGANIZATIONS, new OrganizationMapper());
        for (Organization organization : organizations) {
            associateLocationandHeroesWithOrganization(organization);

        }
        return organizations;
    }

    @Override
    public void updateOrganization(Organization organization) {
        final String UPDATE_ORGANIZATION = "UPDATE organization SET name = ?, description = ?, "
                + "Location_idLocation = ?, contactEmail = ?, contactPhone = ?  WHERE idOrganization = ?";
        jdbc.update(UPDATE_ORGANIZATION,
                organization.getOrganizationName(),
                organization.getOrganizationDescription(),
                organization.getLocation().getLocationId(),
                organization.getOrganizationEmail(),
                organization.getOrganizationPhone(),
                organization.getOrganizationId());

        final String DELETE_HERO_ORGANIZATION = "DELETE FROM HeroOrganization WHERE Organization_idOrganization = ?";
        jdbc.update(DELETE_HERO_ORGANIZATION, organization.getOrganizationId());
        insertOrganizationHero(organization);
    }

    @Override
    @Transactional
    public void deleteOrganizationById(int id) {
        final String DELETE_ORGANIZATION_FROM_HERO = "DELETE FROM HeroOrganization WHERE Organization_idOrganization = ?";
        jdbc.update(DELETE_ORGANIZATION_FROM_HERO, id);

        final String DELETE_ORGANIZATION = "DELETE FROM Organization WHERE idOrganization = ?";
        jdbc.update(DELETE_ORGANIZATION, id);
    }

    //Helper methods
    private void insertOrganizationHero(Organization organization) {
        final String INSERT_HERO_ORGANIZATION = "INSERT INTO "
                + "HeroOrganization(Hero_idHero, Organization_idOrganization) VALUES(?,?)";
        try {
            for (Hero hero : organization.getHeroes()) {
                jdbc.update(INSERT_HERO_ORGANIZATION,
                        hero.getHeroId(),
                        organization.getOrganizationId());
            }
        } catch (NullPointerException ex) {

        }
    }
    public Organization getOrganizationByLocation(Location location){
        final String SELECT_ORGANIZATION_BY_LOCATION = "SELECT * FROM Organization WHERE Location_idLocation=?";
        try{
            Organization organization=jdbc.queryForObject(SELECT_ORGANIZATION_BY_LOCATION, new OrganizationMapper(), location.getLocationId());
        return organization;
        }catch(NullPointerException ex){
            return null;
        }
    }
    @Override
     public List<Organization> getOrganizationsByHero(Hero hero){
        final String SELECT_ORGANIZATIONS_BY_HERO = "SELECT o.idOrganization, o.name, o.Location_idLocation, o.description, o.contactEmail, o.contactPhone FROM Organization o "
                + " JOIN HeroOrganization ho ON ho.Organization_idOrganization=o.idOrganization"
                + " WHERE ho.Hero_idHero=?";
       
            List<Organization> organizations=jdbc.query(SELECT_ORGANIZATIONS_BY_HERO, new OrganizationMapper(), hero.getHeroId());
            for (Organization organization: organizations){
                associateLocationandHeroesWithOrganization(organization);
            }
        return organizations;
      
    }
     
 
    
   
    private void associateLocationandHeroesWithOrganization(Organization organization) {
        final String SELECT_LOCATION_FOR_ORGANIZATION = "SELECT l.idLocation, l.name, l.description, l.address, l.city, l.state, l.country, l.zipcode, l.latitude, l.longitude FROM Location l "
                + "JOIN Organization o ON o.Location_idLocation = l.idLocation WHERE o.idOrganization =?";
        Location thisLocation = jdbc.queryForObject(SELECT_LOCATION_FOR_ORGANIZATION, new LocationMapper(), organization.getOrganizationId());
        organization.setLocation(thisLocation);

        final String GET_HEROES_BY_ORGANIZATION
                = "SELECT idHero, name, description,Superpower_idSuperpower"
                + " FROM Hero h JOIN HeroOrganization ho ON ho.Hero_idHero = h.idHero"
                + " WHERE ho.Organization_idOrganization = ?";
        List<Hero> heroes = jdbc.query(GET_HEROES_BY_ORGANIZATION,
                new HeroMapper(), organization.getOrganizationId());

        if (heroes.isEmpty()) {
            heroes = null;
        } else {
            for (Hero hero : heroes) {
                final String SELECT_SUPERPOWER_FOR_HERO = "SELECT s.idSuperpower, s.name FROM Superpower s "
                        + "JOIN Hero h ON h.Superpower_idSuperpower = s.idSuperpower WHERE h.idHero =?";
                Superpower thisPower = jdbc.queryForObject(SELECT_SUPERPOWER_FOR_HERO, new SuperpowerDaoDB.SuperpowerMapper(), hero.getHeroId());

                hero.setSuperPower(thisPower);
            }

        }
    }

    public static final class OrganizationMapper implements RowMapper<Organization> {

        @Override
        public Organization mapRow(ResultSet rs, int index) throws SQLException {
            Organization organization = new Organization(rs.getInt("idOrganization"));
            organization.setOrganizationName(rs.getString("name"));
            organization.setOrganizationDescription(rs.getString("description"));
            organization.setOrganizationEmail(rs.getString("contactEmail"));
            organization.setOrganizationPhone(rs.getString("contactPhone"));

            return organization;
        }
    }
}
