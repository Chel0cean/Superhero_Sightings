
package com.sg.SuperHeroSightings.dao;

import com.sg.SuperHeroSightings.dto.Location;
import java.util.List;

/**
 *
 * @author Chelsea, Karma, Mohammed, Patrick
 */
public interface LocationDao {
    
    Location getLocationById(int id);

    List<Location> getAllLocations();

    Location addLocation(Location location);

    void updateLocation(Location location);

    void deleteLocationById(int id);
}
