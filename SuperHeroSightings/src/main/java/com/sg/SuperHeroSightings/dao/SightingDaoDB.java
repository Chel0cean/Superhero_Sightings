package com.sg.SuperHeroSightings.dao;

import com.sg.SuperHeroSightings.dao.HeroDaoDB.HeroMapper;
import com.sg.SuperHeroSightings.dao.LocationDaoDB.LocationMapper;
import com.sg.SuperHeroSightings.dto.Hero;
import com.sg.SuperHeroSightings.dto.Location;
import com.sg.SuperHeroSightings.dto.Sighting;
import com.sg.SuperHeroSightings.dto.Superpower;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
public class SightingDaoDB implements SightingDao {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-YYYY"); 

    @Autowired
    JdbcTemplate jdbc;

    //CRUD functions
    @Override
    @Transactional
    public Sighting addSighting(Sighting sighting) {
        final String INSERT_SIGHTING = "INSERT INTO Sighting(date, Hero_idHero, Location_idLocation) "
                + "VALUES(?,?,?)";
        jdbc.update(INSERT_SIGHTING,
                sighting.getDate(),
                sighting.getHero().getHeroId(),
                sighting.getLocation().getLocationId());

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        sighting.setSightingId(newId);
        
         
       sighting.setDateAsString(formatter.format(sighting.getDate()));
       

        return sighting;
    }

    @Override
    public Sighting getSightingById(int id) {
        try {
            final String GET_SIGHTING_BY_ID = "SELECT * FROM Sighting WHERE idSighting = ?";
            Sighting sighting = jdbc.queryForObject(GET_SIGHTING_BY_ID, new SightingMapper(), id);
            sighting = associateHeroesAndLocationsWithSighting(sighting);
            return sighting;
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Sighting> getAllSightings() {

        final String GET_ALL_SIGHTINGS = "SELECT * FROM Sighting";
        List<Sighting> sightings = jdbc.query(GET_ALL_SIGHTINGS, new SightingMapper());
        for (Sighting sighting : sightings) {
            sighting = associateHeroesAndLocationsWithSighting(sighting);
        }
        return sightings;
    }

    @Override
    public void updateSighting(Sighting sighting) {
        final String UPDATE_SIGHTING = "UPDATE sighting SET date = ?, Hero_idHero = ?, "
                + "Location_idLocation = ? WHERE idSighting = ?";
        jdbc.update(UPDATE_SIGHTING,
                sighting.getDate(),
                sighting.getHero().getHeroId(),
                sighting.getLocation().getLocationId(),
                sighting.getSightingId());
        
        
            sighting.setDateAsString(formatter.format(sighting.getDate()));

    }

    @Override
    @Transactional
    public void deleteSightingById(int id) {

        final String DELETE_SIGHTING = "DELETE FROM Sighting WHERE idSighting = ?";
        jdbc.update(DELETE_SIGHTING, id);
    }

    //Helper Functions
    @Override
    public List<Sighting> getSightingsOfHero(Hero hero) {
        final String GET_SIGHTINGS_BY_HEROES
                = "SELECT * FROM Sighting s"
                + " WHERE s.Hero_idHero = ?";
        List<Sighting> sightings = jdbc.query(GET_SIGHTINGS_BY_HEROES,
                new SightingMapper(), hero.getHeroId());

        for (Sighting sighting : sightings) {
            sighting = associateHeroesAndLocationsWithSighting(sighting);
        }
        return sightings;
    }

    @Override
    public List<Sighting> getSightingsAtLocation(Location location) {
        final String GET_SIGHTINGS_BY_Location
                = "SELECT * FROM Sighting s"
                + " WHERE s.Location_idLocation = ?";
        List<Sighting> sightings = jdbc.query(GET_SIGHTINGS_BY_Location,
                new SightingMapper(), location.getLocationId());
        for (Sighting sighting : sightings) {
            sighting = associateHeroesAndLocationsWithSighting(sighting);
        }

        return sightings;
    }

    @Override
    public List<Sighting> getSightingsFromDate(LocalDate date) {
        final String GET_SIGHTINGS_BY_DATE
                = "SELECT * FROM Sighting s"
                + " WHERE s.date = ?";
        List<Sighting> sightings = jdbc.query(GET_SIGHTINGS_BY_DATE,
                new SightingMapper(), date);
        for (Sighting sighting : sightings) {
            sighting = associateHeroesAndLocationsWithSighting(sighting);
        }

        return sightings;
    }

    @Override
    public List<Sighting> getTopTenSightings() {
        final String GET_TOP_TEN_SIGHTINGS
                = " SELECT * FROM Sighting"
                + " ORDER BY Date DESC"
                + " LIMIT 10;";
        List<Sighting> topTenSightings = jdbc.query(GET_TOP_TEN_SIGHTINGS, new SightingMapper());
        return topTenSightings;
        
    }

    private Sighting associateHeroesAndLocationsWithSighting(Sighting sighting) {
        final String GET_LOCATION_FOR_SIGHTING
                = "SELECT l.idLocation, l.name, l.description, l.address, l.city, l.state, l.country, l.zipcode, l.latitude, l.longitude"
                + " FROM Location l JOIN Sighting s ON s.Location_idLocation = l.idLocation"
                + " WHERE idSighting = ?";
        Location location = jdbc.queryForObject(GET_LOCATION_FOR_SIGHTING, new LocationMapper(), sighting.getSightingId());

        sighting.setLocation(location);

        final String GET_HERO_FOR_SIGHTING
                = "SELECT h.idHero, h.name, h.description, h.photoFilename FROM Hero h "
                + " JOIN Sighting s ON s.Hero_idHero = h.idHero"
                + " WHERE s.idSighting = ?";
        Hero hero = jdbc.queryForObject(GET_HERO_FOR_SIGHTING, new HeroMapper(), sighting.getSightingId());

        int heroID = hero.getHeroId();

        final String SELECT_SUPERPOWER_FOR_HERO = "SELECT s.idSuperpower, s.name FROM Superpower s "
                + "JOIN Hero h ON h.Superpower_idSuperpower = s.idSuperpower WHERE h.idHero =?";
        Superpower thisPower = jdbc.queryForObject(SELECT_SUPERPOWER_FOR_HERO, new SuperpowerDaoDB.SuperpowerMapper(), heroID);
        hero.setSuperPower(thisPower);

        sighting.setHero(hero);

        return sighting;
    }

    public static final class SightingMapper implements RowMapper<Sighting> {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-YYYY");

        @Override
        public Sighting mapRow(ResultSet rs, int index) throws SQLException {
            Sighting sighting = new Sighting(rs.getInt("idSighting"));
            sighting.setDate(rs.getDate("date").toLocalDate());
            sighting.setHero(new Hero(rs.getInt("Hero_idHero")));
            sighting.setLocation(new Location(rs.getInt("Location_idLocation")));
            
            
            
            
             sighting.setDateAsString(formatter.format(sighting.getDate()));

            return sighting;
        }
    }

}

