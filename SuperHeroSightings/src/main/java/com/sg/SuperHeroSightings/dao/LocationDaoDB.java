package com.sg.SuperHeroSightings.dao;

import com.sg.SuperHeroSightings.dao.OrganizationDaoDB.OrganizationMapper;
import com.sg.SuperHeroSightings.dto.Location;
import com.sg.SuperHeroSightings.dto.Organization;
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
public class LocationDaoDB implements LocationDao {

    @Autowired
    JdbcTemplate jdbc;
    
      @Override
    @Transactional
    public Location addLocation(Location location) {
        final String INSERT_LOCATION = "INSERT INTO Location(name, description, address, city, state, country, zipcode, latitude, longitude) "
                + "VALUES(?,?,?,?,?,?,?,?,?)";
        jdbc.update(INSERT_LOCATION,
                location.getLocationName(),
                location.getLocationDescription(),
                location.getLocationAddress(),
                location.getLocationCity(),
                location.getLocationState(),
                location.getCountry(),
                location.getZipCode(),
                location.getLatitude(),
                location.getLongitude());

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class
        );
        location.setLocationId(newId);
        return location;
    }

    @Override
    public Location getLocationById(int id) {
        try {
            final String GET_LOCATION_BY_ID = "SELECT * FROM Location WHERE idLocation = ?";
            return jdbc.queryForObject(GET_LOCATION_BY_ID, new LocationMapper(), id);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Location> getAllLocations() {

        final String GET_ALL_LOCATIONS = "SELECT * FROM Location";
        return jdbc.query(GET_ALL_LOCATIONS, new LocationMapper());
    }

  
    @Override
    public void updateLocation(Location location) {
        final String UPDATE_LOCATION = "UPDATE location SET name = ?, description = ?, "
                + "address = ?, city = ?, state = ?, country = ?, zipcode = ?, latitude = ?,"
                + "longitude = ?  WHERE idLocation = ?";
        jdbc.update(UPDATE_LOCATION,
                location.getLocationName(),
                location.getLocationDescription(),
                location.getLocationAddress(),
                location.getLocationCity(),
                location.getLocationState(),
                location.getCountry(),
                location.getZipCode(),
                location.getLatitude(),
                location.getLongitude(),
                location.getLocationId());
    }

    @Override
    @Transactional
    public void deleteLocationById(int id) {

        List<Organization> organizationsAtLocation = new ArrayList();
        List<Integer> organizationIDs = new ArrayList();

        final String GET_ORGANIZATION_BY_LOCATION = "SELECT * FROM Organization WHERE Location_idLocation = ?";
        organizationsAtLocation = jdbc.query(GET_ORGANIZATION_BY_LOCATION, new OrganizationMapper(), id);

        for (Organization i : organizationsAtLocation) {
            organizationIDs.add(i.getOrganizationId());
        }
        for (int j : organizationIDs) {
            final String DELETE_ORGANIZATIONS_FROM_HEROES = "DELETE ho.* FROM HeroOrganization ho "
                    + "Join Organization o ON o.idOrganization=ho.Organization_idOrganization "
                    +" WHERE o.Location_idLocation = ?";
            jdbc.update(DELETE_ORGANIZATIONS_FROM_HEROES, j);
        }

        final String DELETE_ORGANIZATION_BY_LOCATION = "DELETE FROM Organization WHERE Location_idLocation = ?";
        jdbc.update(DELETE_ORGANIZATION_BY_LOCATION, id);

        final String DELETE_SIGHTING_BY_LOCATION = "DELETE FROM Sighting WHERE Location_idLocation = ?";
        jdbc.update(DELETE_SIGHTING_BY_LOCATION, id);

        final String DELETE_LOCATION = "DELETE FROM Location WHERE idLocation = ?";
        jdbc.update(DELETE_LOCATION, id);
    }

    public static final class LocationMapper implements RowMapper<Location> {

        @Override
        public Location mapRow(ResultSet rs, int index) throws SQLException {
            Location location = new Location(rs.getInt("idLocation"));
            location.setLocationName(rs.getString("name"));
            location.setLocationDescription(rs.getString("description"));
            location.setLocationAddress(rs.getString("address"));
            location.setLocationCity(rs.getString("city"));
            location.setLocationState(rs.getString("state"));
            location.setCountry(rs.getString("country"));
            location.setZipCode(rs.getString("zipCode"));
            location.setLatitude(rs.getBigDecimal("latitude"));
            location.setLongitude(rs.getBigDecimal("longitude"));

            return location;
        }
    }
}
