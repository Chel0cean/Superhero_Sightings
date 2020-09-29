package com.sg.SuperHeroSightings.dao;

import com.sg.SuperHeroSightings.TestApplicationConfiguration;
import com.sg.SuperHeroSightings.dto.Hero;
import com.sg.SuperHeroSightings.dto.Location;
import com.sg.SuperHeroSightings.dto.Organization;
import com.sg.SuperHeroSightings.dto.Sighting;
import com.sg.SuperHeroSightings.dto.Superpower;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author ChelseaMiller
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class HeroDaoDBTest {

    @Autowired
    HeroDao heroDao;
    
    @Autowired
    LocationDao locationDao;
    
    @Autowired
    OrganizationDao organizationDao;
    
    @Autowired
    SightingDao sightingDao;
    @Autowired
    SuperpowerDao superpowerDao;

    public HeroDaoDBTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        try {
            List<Superpower> superpowers = superpowerDao.getAllSuperpowers();
            for (Superpower superpower : superpowers) {
                superpowerDao.deleteSuperpowerById(superpower.getSuperPowerId());
            }
        } catch (NullPointerException ex) {

        }

        try {
            List<Location> locations = locationDao.getAllLocations();
            for (Location location : locations) {
                locationDao.deleteLocationById(location.getLocationId());
            }
        } catch (NullPointerException ex) {

        }

        try {
            List<Hero> heroes = heroDao.getAllHeroes();

            for (Hero hero : heroes) {
                heroDao.deleteHeroById(hero.getHeroId());
            }
        } catch (NullPointerException ex) {

        }

        try {
            List<Organization> organizations = organizationDao.getAllOrganizations();

            for (Organization organization : organizations) {
                organizationDao.deleteOrganizationById(organization.getOrganizationId());
            }
        } catch (NullPointerException ex) {

        }

        try {
            List<Sighting> sightings = sightingDao.getAllSightings();

            for (Sighting sighting : sightings) {
                sightingDao.deleteSightingById(sighting.getSightingId());
            }
        } catch (NullPointerException ex) {

        }

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getHeroById method, of class HeroDaoDB.
     */
    @Test
    public void testAddGetHeroById() {
        System.out.println("addHero");
        Superpower flight = new Superpower();
        flight.setSuperPowerName("Flight");
        flight=superpowerDao.addSuperpower(flight);
        Hero Superman = new Hero();
        Superman.setHeroName("Superman");
        Superman.setHeroDescription("faster than a speeding bullet");
        Superman.setSuperPower(flight);

        Superman=heroDao.addHero(Superman);

        Hero result = heroDao.getHeroById(Superman.getHeroId());

        assertEquals(Superman, result);

    }

    /**
     * Test of getAllHeroes method, of class HeroDaoDB.
     */
    @Test
    public void testGetAllHeroes() {
        System.out.println("getAllHeroes");

        Superpower flight = new Superpower();
        flight.setSuperPowerName("Flight");
        flight=superpowerDao.addSuperpower(flight);
        
        Hero Superman = new Hero();
        Superman.setHeroName("Superman");
        Superman.setHeroDescription("faster than a speeding bullet");
        Superman.setSuperPower(flight);

        Superman=heroDao.addHero(Superman);

        Superpower strength = new Superpower();
        strength.setSuperPowerName("Strength");
        strength=superpowerDao.addSuperpower(strength);

        Hero theHulk = new Hero();
        theHulk.setHeroName("The Hulk");
        theHulk.setHeroDescription("You wouldn't like him when he's angry.");
        theHulk.setSuperPower(strength);

        theHulk=heroDao.addHero(theHulk);

        List<Hero> heroes = new ArrayList();
        heroes.add(Superman);
        heroes.add(theHulk);

        List<Hero> result = heroDao.getAllHeroes();

        assertEquals(heroes, result);
    }

    @Test
    public void testUpdateHero() {
        System.out.println("updateHero");
        
        Superpower flight = new Superpower();
        flight.setSuperPowerName("Flight");
        flight=superpowerDao.addSuperpower(flight);
        
        
        Hero Superman = new Hero();
        Superman.setHeroName("Superman");
        Superman.setHeroDescription("faster than a speeding bullet");
        Superman.setSuperPower(flight);

        Superman=heroDao.addHero(Superman);
        
        Hero oldResult= heroDao.getHeroById(Superman.getHeroId());
        
        Superman.setHeroDescription("Looks a lot like Clark Kent.");
        
        heroDao.updateHero(Superman);
        
        Hero updatedResult = heroDao.getHeroById(Superman.getHeroId());
        
        assertNotEquals(oldResult, updatedResult);
        
        
    }

   
    @Test
    public void testDeleteHeroById() {
        System.out.println("deleteHeroById");
        
      Superpower flight = new Superpower();
        flight.setSuperPowerName("Flight");
        flight=superpowerDao.addSuperpower(flight);
        
        
        Hero Superman = new Hero();
        Superman.setHeroName("Superman");
        Superman.setHeroDescription("faster than a speeding bullet");
        Superman.setSuperPower(flight);

        Superman=heroDao.addHero(Superman);
        
        
        heroDao.deleteHeroById(Superman.getHeroId());
        
        assertNull(heroDao.getHeroById(Superman.getHeroId()));
        
       
    }

    @Test
    public void testGetHeroesBySuperpower() {
        System.out.println("getHeroesBySuperpower");
        
        Superpower flight = new Superpower();
        flight.setSuperPowerName("Flight");
        flight=superpowerDao.addSuperpower(flight);
        
       Hero Superman = new Hero();
        Superman.setHeroName("Superman");
        Superman.setHeroDescription("faster than a speeding bullet");
        Superman.setSuperPower(flight);
        
        Superman=heroDao.addHero(Superman);
        
        Superpower strength = new Superpower();
        strength.setSuperPowerName("Strength");
        strength=superpowerDao.addSuperpower(strength);

        Hero theHulk = new Hero();
        theHulk.setHeroName("The Hulk");
        theHulk.setHeroDescription("You wouldn't like him when he's angry.");
        theHulk.setSuperPower(strength);

        theHulk=heroDao.addHero(theHulk);
        
        Hero Rogue = new Hero();
        Rogue.setHeroName("Rogue");
        Rogue.setHeroDescription("A southern belle.");
        Rogue.setSuperPower(flight);

        Rogue=heroDao.addHero(Rogue);
        
        List <Hero> allHeroes = heroDao.getAllHeroes();
        
        List<Hero> heroesWhoCanFly=heroDao.getHeroesBySuperpower(flight);
        
        assertNotEquals(allHeroes, heroesWhoCanFly);
        
        assertTrue(heroesWhoCanFly.size()==2);
        
       
    }

 

}
