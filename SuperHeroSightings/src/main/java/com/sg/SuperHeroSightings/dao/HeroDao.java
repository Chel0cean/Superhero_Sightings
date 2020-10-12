package com.sg.SuperHeroSightings.dao;

import com.sg.SuperHeroSightings.dto.Hero;
import com.sg.SuperHeroSightings.dto.Location;
import com.sg.SuperHeroSightings.dto.Organization;
import com.sg.SuperHeroSightings.dto.Superpower;
import java.util.List;

/**
 *
 * @author Chelsea, Karma, Mohammed, Patrick
 */
public interface HeroDao {

    Hero getHeroById(int id);

    List<Hero> getAllHeroes();

    Hero addHero(Hero hero);

    void updateHero(Hero hero);

    void deleteHeroById(int id);

    List<Hero> getHeroesBySuperpower(Superpower superPower);

    public void insertHeroOrganization(Hero hero, List<Integer> organizationIds);

    public List<Hero> getHeroesByOrganization(Organization organization);

    public void associateOrgsForHero(Hero hero, List<Organization> orgs);

}