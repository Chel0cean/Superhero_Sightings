
package com.sg.SuperHeroSightings.dao;

import com.sg.SuperHeroSightings.dto.Hero;
import com.sg.SuperHeroSightings.dto.Location;
import com.sg.SuperHeroSightings.dto.Organization;
import java.util.List;

/**
 *
 * @author Chelsea, Karma, Mohammed, Patrick
 */
public interface OrganizationDao {
    Organization getOrganizationById(int id);

    List<Organization> getAllOrganizations();

    Organization addOrganization(Organization organization);

    void updateOrganization(Organization organization);

    void deleteOrganizationById(int id);

    public List<Organization> getOrganizationsByHero(Hero hero);

     

}
