package com.sg.SuperHeroSightings.dao;

import com.sg.SuperHeroSightings.dto.Hero;
import com.sg.SuperHeroSightings.dto.Location;
import com.sg.SuperHeroSightings.dto.Sighting;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Chelsea, Karma, Mohammed, Patrick
 */
public interface SightingDao {

    Sighting getSightingById(int id);

    List<Sighting> getAllSightings();

    Sighting addSighting(Sighting sighting);

    void updateSighting(Sighting sighting);

    void deleteSightingById(int id);

    List<Sighting> getSightingsOfHero(Hero hero);

    List<Sighting> getSightingsAtLocation(Location location);
    
    List<Sighting> getSightingsFromDate(LocalDate date);
    
    public List<Sighting> getTopTenSightings();
}
