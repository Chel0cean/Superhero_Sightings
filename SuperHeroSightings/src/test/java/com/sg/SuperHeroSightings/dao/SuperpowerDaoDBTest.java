
package com.sg.SuperHeroSightings.dao;

import com.sg.SuperHeroSightings.TestApplicationConfiguration;
import com.sg.SuperHeroSightings.dto.Hero;
import com.sg.SuperHeroSightings.dto.Location;
import com.sg.SuperHeroSightings.dto.Organization;
import com.sg.SuperHeroSightings.dto.Sighting;
import com.sg.SuperHeroSightings.dto.Superpower;
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
 * @author MohammedChowdhury
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class SuperpowerDaoDBTest {

    @Autowired
    HeroDaoDB heroDao;
    @Autowired
    LocationDaoDB locationDao;
    @Autowired
    OrganizationDaoDB organizationDao;
    @Autowired
    SightingDaoDB sightingDao;
    @Autowired
    SuperpowerDaoDB superpowerDao;

    public SuperpowerDaoDBTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
             List<Superpower> superpowers = superpowerDao.getAllSuperpowers();
        for(Superpower superpower : superpowers) {
            superpowerDao.deleteSuperpowerById(superpower.getSuperPowerId());
        }
        
        List<Location> locations = locationDao.getAllLocations();
        for(Location location : locations) {
            locationDao.deleteLocationById(location.getLocationId());
        }
        
        List<Hero> heroes = heroDao.getAllHeroes();
        for(Hero hero : heroes) {
            heroDao.deleteHeroById(hero.getHeroId());
        }
         List<Organization> organizations = organizationDao.getAllOrganizations();
        for(Organization organization : organizations) {
            organizationDao.deleteOrganizationById(organization.getOrganizationId());
        }
         List<Sighting> sightings = sightingDao.getAllSightings();
        for(Sighting sighting : sightings) {
            sightingDao.deleteSightingById(sighting.getSightingId());
        }
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getSuperpowerById method, of class SuperpowerDaoDB.
     */
    @Test
    public void testAddGetSuperpowerById() {
        System.out.println("addGetSuperpowerById");
       
        Superpower flight = new Superpower();
        flight.setSuperPowerName("Flight");
        
        flight = superpowerDao.addSuperpower(flight);
        Superpower getResult = superpowerDao.getSuperpowerById(flight.getSuperPowerId());
        
        assertEquals(flight, getResult);
        
    }

    /**
     * Test of getAllSuperpowers method, of class SuperpowerDaoDB.
     */
    @Test
    public void testGetAllSuperpowers() {
        
        Superpower superpower1 = new Superpower();
        superpower1.setSuperPowerName("Flight");

        Superpower superpower2 = new Superpower();
        superpower2.setSuperPowerName("Strength");
        
        superpower1 = superpowerDao.addSuperpower(superpower1);
        superpower2 = superpowerDao.addSuperpower(superpower2);
        
        List<Superpower> superpowers = new ArrayList();
        
        superpowers.add(superpower1);
        superpowers.add(superpower2);
                
        
        List<Superpower> result = superpowerDao.getAllSuperpowers();
        
      
        assertEquals(superpowers, result);
      
    }

    
    @Test
    public void testUpdateSuperpower() {
        System.out.println("updateSuperpower");
        
        
        String oldPower = "Flight";
        String newPower = "Strength";
     
        Superpower ability = new Superpower();
        ability.setSuperPowerName(oldPower);
        
        ability = superpowerDao.addSuperpower(ability);
        
        ability.setSuperPowerName(newPower);
        
       superpowerDao.updateSuperpower(ability);
       
       Superpower result = superpowerDao.getSuperpowerById(ability.getSuperPowerId());
       
       assertEquals(newPower,result.getSuperPowerName());
       
       
        
        
    }

    /**
     * Test of deleteSuperpowerById method, of class SuperpowerDaoDB.
     */
    @Test
    public void testDeleteSuperpowerById() {
        System.out.println("deleteSuperpowerById");
        
        Superpower flight = new Superpower();
        flight.setSuperPowerName("Flight");
        
        flight = superpowerDao.addSuperpower(flight);
        
        superpowerDao.deleteSuperpowerById(flight.getSuperPowerId());
        
        Superpower result = superpowerDao.getSuperpowerById(flight.getSuperPowerId());
        
        assertNull(result);
    }

}
