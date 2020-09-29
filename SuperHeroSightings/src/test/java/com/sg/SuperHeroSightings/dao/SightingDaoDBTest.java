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
import java.time.LocalDate;
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
public class SightingDaoDBTest {
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
    
    public SightingDaoDBTest() {
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
    public void testAddGetSightingById() {
        System.out.println("addGetSightingById");
     
        Superpower flight = new Superpower();
        flight.setSuperPowerName("Flight");
        flight=superpowerDao.addSuperpower(flight);
        Hero Superman = new Hero();
        Superman.setHeroName("Superman");
        Superman.setHeroDescription("faster than a speeding bullet");
        Superman.setSuperPower(flight);

        Superman=heroDao.addHero(Superman);
        
        
        BigDecimal latitude = new BigDecimal("35.555000");
        BigDecimal longitude = new BigDecimal("85.555000");
   
        Location theMallofAmerica = new Location();
        theMallofAmerica.setLocationDescription("Not just for shopping");
        theMallofAmerica.setLocationAddress("1234 washington way");
        theMallofAmerica.setLocationCity("D.C");
        theMallofAmerica.setLocationState("Va");
        theMallofAmerica.setCountry("USA");
        theMallofAmerica.setZipCode("11111");
        theMallofAmerica.setLocationName("Mall of America");
        theMallofAmerica.setLatitude(latitude);
        theMallofAmerica.setLongitude(longitude);

        theMallofAmerica = locationDao.addLocation(theMallofAmerica);

        
        Sighting currentSighting = new Sighting();
        
        LocalDate date = LocalDate.parse("2017-02-05");
        
        currentSighting.setDate(date);
        currentSighting.setHero(Superman);
        currentSighting.setLocation(theMallofAmerica);
        
        
        currentSighting=sightingDao.addSighting(currentSighting);
        
        
        Sighting result = sightingDao.getSightingById(currentSighting.getSightingId());
        
        
        
        assertEquals(currentSighting, result);
        
        
    }

   
    @Test
    public void testGetAllSightings() {
        System.out.println("getAllSightings");
        
        Superpower flight = new Superpower();
        flight.setSuperPowerName("Flight");
        flight=superpowerDao.addSuperpower(flight);
        Hero Superman = new Hero();
        Superman.setHeroName("Superman");
        Superman.setHeroDescription("faster than a speeding bullet");
        Superman.setSuperPower(flight);

        Superman=heroDao.addHero(Superman);
        
        
        BigDecimal latitude = new BigDecimal("35.555000");
        BigDecimal longitude = new BigDecimal("85.555000");
   
        Location theMallofAmerica = new Location();
        theMallofAmerica.setLocationDescription("Not just for shopping");
        theMallofAmerica.setLocationAddress("1234 washington way");
        theMallofAmerica.setLocationCity("D.C");
        theMallofAmerica.setLocationState("Va");
        theMallofAmerica.setCountry("USA");
        theMallofAmerica.setZipCode("11111");
        theMallofAmerica.setLocationName("Mall of America");
        theMallofAmerica.setLatitude(latitude);
        theMallofAmerica.setLongitude(longitude);

        theMallofAmerica = locationDao.addLocation(theMallofAmerica);

        
        Sighting firstSighting = new Sighting();
        
        LocalDate date = LocalDate.parse("2017-02-05");
        
        firstSighting.setDate(date);
        firstSighting.setHero(Superman);
        firstSighting.setLocation(theMallofAmerica);
        
        firstSighting=sightingDao.addSighting(firstSighting);
        
        
        Sighting secondSighting = new Sighting();
        
        LocalDate date2 = LocalDate.parse("2017-03-10");
        
        secondSighting.setDate(date2);
        secondSighting.setHero(Superman);
        secondSighting.setLocation(theMallofAmerica);
        
        secondSighting=sightingDao.addSighting(secondSighting);
        
        List<Sighting> sightings = new ArrayList();
        
        sightings.add(firstSighting);
        sightings.add(secondSighting);
        
        List<Sighting> result = sightingDao.getAllSightings();
        
        assertEquals(sightings, result);
       
    }

    @Test
    public void testUpdateSighting() {
        System.out.println("updateSighting");
        
        
        Superpower flight = new Superpower();
        flight.setSuperPowerName("Flight");
        flight=superpowerDao.addSuperpower(flight);
        Hero Superman = new Hero();
        Superman.setHeroName("Superman");
        Superman.setHeroDescription("faster than a speeding bullet");
        Superman.setSuperPower(flight);

        Superman=heroDao.addHero(Superman);
        
        
        BigDecimal latitude = new BigDecimal("35.555000");
        BigDecimal longitude = new BigDecimal("85.555000");
   
        Location theMallofAmerica = new Location();
        theMallofAmerica.setLocationDescription("Not just for shopping");
        theMallofAmerica.setLocationAddress("1234 washington way");
        theMallofAmerica.setLocationCity("D.C");
        theMallofAmerica.setLocationState("Va");
        theMallofAmerica.setCountry("USA");
        theMallofAmerica.setZipCode("11111");
        theMallofAmerica.setLocationName("Mall of America");
        theMallofAmerica.setLatitude(latitude);
        theMallofAmerica.setLongitude(longitude);

        theMallofAmerica = locationDao.addLocation(theMallofAmerica);

        
        Sighting currentSighting = new Sighting();
        
        LocalDate date = LocalDate.parse("2017-02-05");
        
        currentSighting.setDate(date);
        currentSighting.setHero(Superman);
        currentSighting.setLocation(theMallofAmerica);
        
        
        currentSighting=sightingDao.addSighting(currentSighting);
        
        
        LocalDate newDate = LocalDate.parse("2017-02-03");
        
        currentSighting.setDate(newDate);
        
        sightingDao.updateSighting(currentSighting);
        
        LocalDate result = sightingDao.getSightingById(currentSighting.getSightingId()).getDate();
        
        
        assertEquals(newDate, result);
        
    }

   @Test
    public void testDeleteSightingById() {
        System.out.println("deleteSightingById");
       
       Superpower flight = new Superpower();
        flight.setSuperPowerName("Flight");
        
        flight=superpowerDao.addSuperpower(flight);
        
        Hero Superman = new Hero();
        Superman.setHeroName("Superman");
        Superman.setHeroDescription("faster than a speeding bullet");
        Superman.setSuperPower(flight);

        Superman=heroDao.addHero(Superman);
        
        
        BigDecimal latitude = new BigDecimal("35.555000");
        BigDecimal longitude = new BigDecimal("85.555000");
   
        Location theMallofAmerica = new Location();
        theMallofAmerica.setLocationDescription("Not just for shopping");
        theMallofAmerica.setLocationAddress("1234 washington way");
        theMallofAmerica.setLocationCity("D.C");
        theMallofAmerica.setLocationState("Va");
        theMallofAmerica.setCountry("USA");
        theMallofAmerica.setZipCode("11111");
        theMallofAmerica.setLocationName("Mall of America");
        theMallofAmerica.setLatitude(latitude);
        theMallofAmerica.setLongitude(longitude);

        theMallofAmerica = locationDao.addLocation(theMallofAmerica);

        
        Sighting currentSighting = new Sighting();
        
        LocalDate date = LocalDate.parse("2017-02-05");
        
        currentSighting.setDate(date);
        currentSighting.setHero(Superman);
        currentSighting.setLocation(theMallofAmerica);
        
        
        currentSighting=sightingDao.addSighting(currentSighting); 
        
        sightingDao.deleteSightingById(currentSighting.getSightingId());
        
        Sighting result = sightingDao.getSightingById(currentSighting.getSightingId());
        
        assertNull(result);
        
    }

    /**
     * Test of getSightingsOfHero method, of class SightingDaoDB.
 */
    @Test
    public void testGetSightingsOfHero() {
        System.out.println("getSightingsOfHero");
        
        Superpower flight = new Superpower();
        flight.setSuperPowerName("Flight");
        flight=superpowerDao.addSuperpower(flight);
        Hero Superman = new Hero();
        Superman.setHeroName("Superman");
        Superman.setHeroDescription("faster than a speeding bullet");
        Superman.setSuperPower(flight);

        Superman=heroDao.addHero(Superman);
        
        
        BigDecimal latitude = new BigDecimal("35.555000");
        BigDecimal longitude = new BigDecimal("85.555000");
   
        Location theMallofAmerica = new Location();
        theMallofAmerica.setLocationDescription("Not just for shopping");
        theMallofAmerica.setLocationAddress("1234 washington way");
        theMallofAmerica.setLocationCity("D.C");
        theMallofAmerica.setLocationState("Va");
        theMallofAmerica.setCountry("USA");
        theMallofAmerica.setZipCode("11111");
        theMallofAmerica.setLocationName("Mall of America");
        theMallofAmerica.setLatitude(latitude);
        theMallofAmerica.setLongitude(longitude);

        theMallofAmerica = locationDao.addLocation(theMallofAmerica);
        
        
        Superpower strength = new Superpower();
        strength.setSuperPowerName("Strength");
        strength=superpowerDao.addSuperpower(strength);

        Hero theHulk = new Hero();
        theHulk.setHeroName("The Hulk");
        theHulk.setHeroDescription("You wouldn't like him when he's angry.");
        theHulk.setSuperPower(strength);

        theHulk=heroDao.addHero(theHulk);

        
        Sighting firstSighting = new Sighting();
        
        LocalDate date = LocalDate.parse("2017-02-05");
        
        firstSighting.setDate(date);
        firstSighting.setHero(Superman);
        firstSighting.setLocation(theMallofAmerica);
        
        firstSighting=sightingDao.addSighting(firstSighting);
        
        
        Sighting secondSighting = new Sighting();
        
        LocalDate date2 = LocalDate.parse("2017-03-10");
        
        secondSighting.setDate(date2);
        secondSighting.setHero(Superman);
        secondSighting.setLocation(theMallofAmerica);
        
        secondSighting=sightingDao.addSighting(secondSighting);
        
        
        Sighting thirdSighting = new Sighting();
        
       
        
        thirdSighting.setDate(date);
        thirdSighting.setHero(theHulk);
        thirdSighting.setLocation(theMallofAmerica);
        
        thirdSighting=sightingDao.addSighting(thirdSighting);
        
        
        
        List<Sighting> supermanSightings = new ArrayList();
        
        supermanSightings.add(firstSighting);
        supermanSightings.add(secondSighting);
        
        
        
        List<Sighting> result = sightingDao.getSightingsOfHero(Superman);
        
        assertEquals(supermanSightings, result);
    }

    /**
     * Test of getSightingsAtLocation method, of class SightingDaoDB.
    */
    @Test
    public void testGetSightingsAtLocation() {
        
       Superpower flight = new Superpower();
        flight.setSuperPowerName("Flight");
        flight=superpowerDao.addSuperpower(flight);
        Hero Superman = new Hero();
        Superman.setHeroName("Superman");
        Superman.setHeroDescription("faster than a speeding bullet");
        Superman.setSuperPower(flight);

        Superman=heroDao.addHero(Superman);
        
        
        BigDecimal latitude = new BigDecimal("35.555000");
        BigDecimal longitude = new BigDecimal("85.555000");
   
        Location theMallofAmerica = new Location();
        theMallofAmerica.setLocationDescription("Not just for shopping");
        theMallofAmerica.setLocationAddress("1234 washington way");
        theMallofAmerica.setLocationCity("D.C");
        theMallofAmerica.setLocationState("Va");
        theMallofAmerica.setCountry("USA");
        theMallofAmerica.setZipCode("11111");
        theMallofAmerica.setLocationName("Mall of America");
        theMallofAmerica.setLatitude(latitude);
        theMallofAmerica.setLongitude(longitude);

        theMallofAmerica = locationDao.addLocation(theMallofAmerica);

        
        Sighting firstSighting = new Sighting();
        
        LocalDate date = LocalDate.parse("2017-02-05");
        
        firstSighting.setDate(date);
        firstSighting.setHero(Superman);
        firstSighting.setLocation(theMallofAmerica);
        
        firstSighting=sightingDao.addSighting(firstSighting);
        
        
        Sighting secondSighting = new Sighting();
        
        LocalDate date2 = LocalDate.parse("2017-03-10");
        
        secondSighting.setDate(date2);
        secondSighting.setHero(Superman);
        secondSighting.setLocation(theMallofAmerica);
        
        secondSighting=sightingDao.addSighting(secondSighting);
        
        
        
        BigDecimal latitude2 = new BigDecimal("17.555000");
        BigDecimal longitude2 = new BigDecimal("96.555000");
   
        Location tacoBell = new Location();
        tacoBell.setLocationDescription("Not just for tacos");
        tacoBell.setLocationAddress("1234 taco way");
        tacoBell.setLocationCity("Manhattan");
        tacoBell.setLocationState("NY");
        tacoBell.setCountry("USA");
        tacoBell.setZipCode("11111");
        tacoBell.setLocationName("Taco Bell");
        tacoBell.setLatitude(latitude2);
        tacoBell.setLongitude(longitude2);

        tacoBell = locationDao.addLocation(tacoBell);
        
        
        Sighting thirdSighting = new Sighting();
        
        thirdSighting.setDate(date2);
        thirdSighting.setHero(Superman);
        thirdSighting.setLocation(tacoBell);
        
        thirdSighting=sightingDao.addSighting(thirdSighting);
        
        
        
        List<Sighting> sightingsAtMallofAmerica = new ArrayList();
        
        sightingsAtMallofAmerica.add(firstSighting);
        sightingsAtMallofAmerica.add(secondSighting);
        
        List<Sighting> result = sightingDao.getSightingsAtLocation(theMallofAmerica);
        
        assertEquals(sightingsAtMallofAmerica, result);
    
        
    }

    @Test
    public void testGetSightingsFromDate() {
       System.out.println("getSightingsFromDate");
       
     Superpower flight = new Superpower();
        flight.setSuperPowerName("Flight");
        flight=superpowerDao.addSuperpower(flight);
        Hero Superman = new Hero();
        Superman.setHeroName("Superman");
        Superman.setHeroDescription("faster than a speeding bullet");
        Superman.setSuperPower(flight);

        Superman=heroDao.addHero(Superman);
        
        
        BigDecimal latitude = new BigDecimal("35.555000");
        BigDecimal longitude = new BigDecimal("85.555000");
   
        Location theMallofAmerica = new Location();
        theMallofAmerica.setLocationDescription("Not just for shopping");
        theMallofAmerica.setLocationAddress("1234 washington way");
        theMallofAmerica.setLocationCity("D.C");
        theMallofAmerica.setLocationState("Va");
        theMallofAmerica.setCountry("USA");
        theMallofAmerica.setZipCode("11111");
        theMallofAmerica.setLocationName("Mall of America");
        theMallofAmerica.setLatitude(latitude);
        theMallofAmerica.setLongitude(longitude);

        theMallofAmerica = locationDao.addLocation(theMallofAmerica);

        
        Sighting firstSighting = new Sighting();
        
        LocalDate date = LocalDate.parse("2017-02-05");
        
        firstSighting.setDate(date);
        firstSighting.setHero(Superman);
        firstSighting.setLocation(theMallofAmerica);
        
        firstSighting=sightingDao.addSighting(firstSighting);
        
        
        Sighting secondSighting = new Sighting();
        
        LocalDate date2 = LocalDate.parse("2017-03-10");
        
        secondSighting.setDate(date2);
        secondSighting.setHero(Superman);
        secondSighting.setLocation(theMallofAmerica);
        
        secondSighting=sightingDao.addSighting(secondSighting);
        
         
        Superpower strength = new Superpower();
        strength.setSuperPowerName("Strength");
        strength=superpowerDao.addSuperpower(strength);

        Hero theHulk = new Hero();
        theHulk.setHeroName("The Hulk");
        theHulk.setHeroDescription("You wouldn't like him when he's angry.");
        theHulk.setSuperPower(strength);

        theHulk=heroDao.addHero(theHulk);
        
        Sighting thirdSighting = new Sighting();
        
        thirdSighting.setDate(date2);
        thirdSighting.setHero(theHulk);
        thirdSighting.setLocation(theMallofAmerica);
        
        thirdSighting=sightingDao.addSighting(thirdSighting);
        
        List<Sighting> sightingsFromDate2 = new ArrayList();
        sightingsFromDate2.add(secondSighting);
        sightingsFromDate2.add(thirdSighting);
        
        List<Sighting> result = sightingDao.getSightingsFromDate(date2);
        
       
        assertEquals(sightingsFromDate2, result);
        
        
    }
    
}
