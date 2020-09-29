package com.sg.SuperHeroSightings.dao;

import com.sg.SuperHeroSightings.dao.HeroDaoDB.HeroMapper;
import com.sg.SuperHeroSightings.dto.Superpower;
import com.sg.SuperHeroSightings.dto.Hero;
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
public class SuperpowerDaoDB implements SuperpowerDao {

    @Autowired
    JdbcTemplate jdbc;
    
    @Override
    @Transactional
    public Superpower addSuperpower(Superpower superPower) {
        final String INSERT_SUPERPOWER = "INSERT INTO Superpower(name) "
                + "VALUES(?)";
        jdbc.update(INSERT_SUPERPOWER,
                superPower.getSuperPowerName());

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        superPower.setSuperPowerId(newId);
        return superPower;
    }

    @Override
    public Superpower getSuperpowerById(int id) {
        try {
            final String GET_SUPERPOWER_BY_ID = "SELECT * FROM Superpower WHERE idSuperpower = ?";
            return jdbc.queryForObject(GET_SUPERPOWER_BY_ID, new SuperpowerMapper(), id);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Superpower> getAllSuperpowers() {
        final String GET_ALL_SUPERPOWERS = "SELECT * FROM Superpower";
        return jdbc.query(GET_ALL_SUPERPOWERS, new SuperpowerMapper());
    }

    @Override
    public void updateSuperpower(Superpower superPower) {
        final String UPDATE_SUPERPOWER = "UPDATE Superpower SET name = ?"
                + "WHERE idSuperpower = ?";
        jdbc.update(UPDATE_SUPERPOWER,
                superPower.getSuperPowerName(),
                superPower.getSuperPowerId());
    }

    @Override
    @Transactional
    public void deleteSuperpowerById(int id) {
        List<Hero> heroesWithSuperPower = new ArrayList();
        List<Integer> heroIDs = new ArrayList();

        final String GET_ALL_HEROES_BY_SUPERPOWER = "SELECT * FROM Hero WHERE Superpower_idSuperpower = ?";

        heroesWithSuperPower = jdbc.query(GET_ALL_HEROES_BY_SUPERPOWER, new HeroMapper(), id);

        for (Hero i : heroesWithSuperPower) {
            heroIDs.add(i.getHeroId());
        }
        for (int j : heroIDs) {
            final String DELETE_HEROES_FROM_ORGANIZATION = "DELETE FROM HeroOrganization "
                    + "WHERE Hero_idHero = ?";
            jdbc.update(DELETE_HEROES_FROM_ORGANIZATION, j);

            final String DELETE_SIGHTING = "DELETE FROM Sighting WHERE Hero_idHero = ?";
            jdbc.update(DELETE_SIGHTING, j);

        }
        
        
        final String DELETE_HERO = "DELETE FROM Hero WHERE Superpower_idSuperpower = ?";
        jdbc.update(DELETE_HERO, id);

        final String DELETE_SUPERPOWER = "DELETE FROM Superpower WHERE idSuperpower = ?";
        jdbc.update(DELETE_SUPERPOWER, id);
    }

    public static final class SuperpowerMapper implements RowMapper<Superpower> {

        @Override
        public Superpower mapRow(ResultSet rs, int index) throws SQLException {
            Superpower superPower = new Superpower();
            superPower.setSuperPowerId(rs.getInt("idSuperpower"));
            superPower.setSuperPowerName(rs.getString("name"));

            return superPower;
        }
    }

}
