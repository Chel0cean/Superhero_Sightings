/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.SuperHeroSightings.dao;

import com.sg.SuperHeroSightings.TestApplicationConfiguration;
import com.sg.SuperHeroSightings.dto.Hero;
import com.sg.SuperHeroSightings.dto.Location;
import com.sg.SuperHeroSightings.dto.Organization;
import com.sg.SuperHeroSightings.dto.Sighting;
import com.sg.SuperHeroSightings.dto.Superpower;
import java.math.BigDecimal;
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
public class LocationDaoDBTest {   
    
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
    
    
    public LocationDaoDBTest() {
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
    
    
       @Test
    public void testAddGetLocation() {
        
        
        System.out.println("addLocation");
 
        Location location1 = new Location();
        location1.setLocationName("Elmhurst Hospital");
        location1.setLocationDescription("Hospital");
        location1.setLocationAddress("79-01 Broadway, Elmhurst, NY 11373");
        location1.setLocationCity("Elmhurst");
        location1.setLocationState("Ny");
        location1.setCountry("USA");
        location1.setZipCode("11373");
        location1.setLatitude(new BigDecimal("40.744715"));
        location1.setLongitude(new BigDecimal("-73.885605"));
     
        location1 = locationDao.addLocation(location1);
        Location location2 = locationDao.getLocationById(location1.getLocationId());
      
        assertEquals(location1, location2);
    }

    @Test
    public void testGetAllLocations() {
        System.out.println("getAllLocations");
      
        
             Location location1 = new Location();
        location1.setLocationName("Elmhurst Hospital");
        location1.setLocationDescription("Hospital");
        location1.setLocationAddress("79-01 Broadway, Elmhurst, NY 11373");
        location1.setLocationCity("Elmhurst");
        location1.setLocationState("Ny");
        location1.setCountry("USA");
        location1.setZipCode("11373");
        location1.setLatitude(new BigDecimal("40.744715"));
        location1.setLongitude(new BigDecimal("-73.885605"));
        location1 = locationDao.addLocation(location1);

        Location location2 = new Location();
        location2.setLocationName("Elmhurst");
        location2.setLocationDescription("Park");
        location2.setLocationAddress("79-03 Broadway, Elmhurst, NY 11373");
        location2.setLocationCity("Elmhurst");
        location2.setLocationState("Ny");
        location2.setCountry("USA");
        location2.setZipCode("11373");
        location2.setLatitude(new BigDecimal("40.744715"));
        location2.setLongitude(new BigDecimal("-73.885605"));
        location2 = locationDao.addLocation(location2);

       
        List<Location> listOfLocations = locationDao.getAllLocations();
        int size = 2;
        int actual = listOfLocations.size();
      
        assertEquals(actual, size);
    }

  
    @Test
    public void testUpdateLocation() {
        System.out.println("updateLocation");
  
        
        Location location1 = new Location();
        location1.setLocationName("Elmhurst Hospital");
        location1.setLocationDescription("Hospital");
        location1.setLocationAddress("79-01 Broadway, Elmhurst, NY 11373");
        location1.setLocationCity("Elmhurst");
        location1.setLocationState("Ny");
        location1.setCountry("USA");
        location1.setZipCode("11373");
        location1.setLatitude(new BigDecimal("40.744715"));
        location1.setLongitude(new BigDecimal("-73.885605"));
       
        location1 = locationDao.addLocation(location1);
        location1.setZipCode("11354");
        location1.setLocationState("NY");
        locationDao.updateLocation(location1);
        Location updatedLocation = locationDao.getLocationById(location1.getLocationId());
        
        assertEquals(location1, updatedLocation);
        
        
    }

    @Test
    public void testDeleteLocationById() {
        System.out.println("deleteLocationById");
      
        
              Location location1 = new Location();
        location1.setLocationName("Elmhurst Hospital");
        location1.setLocationDescription("Hospital");
        location1.setLocationAddress("79-01 Broadway, Elmhurst, NY 11373");
        location1.setLocationCity("Elmhurst");
        location1.setLocationState("Ny");
        location1.setCountry("USA");
        location1.setZipCode("11373");
        location1.setLatitude(new BigDecimal("40.744715"));
        location1.setLongitude(new BigDecimal("-73.885605"));
      
        location1 = locationDao.addLocation(location1);
        locationDao.deleteLocationById(location1.getLocationId());
        location1 = locationDao.getLocationById(location1.getLocationId());
        
        assertNull(location1);
    }
    
}
