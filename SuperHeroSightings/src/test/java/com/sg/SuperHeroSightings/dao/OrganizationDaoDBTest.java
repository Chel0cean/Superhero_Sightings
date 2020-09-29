
package com.sg.SuperHeroSightings.dao;

import com.sg.SuperHeroSightings.TestApplicationConfiguration;
import com.sg.SuperHeroSightings.dto.Hero;
import com.sg.SuperHeroSightings.dto.Location;
import com.sg.SuperHeroSightings.dto.Organization;
import com.sg.SuperHeroSightings.dto.Sighting;
import com.sg.SuperHeroSightings.dto.Superpower;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.math.BigDecimal;
import java.util.ArrayList;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author ChelseaMiller
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class OrganizationDaoDBTest {

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

    public OrganizationDaoDBTest() {
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
     * Test of getOrganizationById method, of class OrganizationDaoDB.
     */
    @Test
    public void testAddGetOrganizationById() {
      

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

        Organization crimeFightersUnited = new Organization();

        crimeFightersUnited.setOrganizationName("Crime Fighters United");
        crimeFightersUnited.setOrganizationDescription("Hidden under the Mall of America");
        crimeFightersUnited.setOrganizationEmail("emailus@nowhere");
        crimeFightersUnited.setOrganizationPhone("5555555");
        crimeFightersUnited.setLocation(theMallofAmerica);

        crimeFightersUnited = organizationDao.addOrganization(crimeFightersUnited);
        
        Organization result = organizationDao.getOrganizationById(crimeFightersUnited.getOrganizationId());
        
        assertEquals(crimeFightersUnited, result);
    }
    @Test public void testGetAllOrganizations() {
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

        Organization crimeFightersUnited = new Organization();

        crimeFightersUnited.setOrganizationName("Crime Fighters United");
        crimeFightersUnited.setOrganizationDescription("Hidden under the Mall of America");
        crimeFightersUnited.setOrganizationEmail("emailus@nowhere");
        crimeFightersUnited.setOrganizationPhone("5555555");
        crimeFightersUnited.setLocation(theMallofAmerica);

        crimeFightersUnited = organizationDao.addOrganization(crimeFightersUnited);
        
        
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

        Organization justiceLeague = new Organization();

        justiceLeague.setOrganizationName("Justice League");
        justiceLeague.setOrganizationDescription("Looks like a taco bell");
        justiceLeague.setOrganizationEmail("emailus@nowhere");
        justiceLeague.setOrganizationPhone("5555555");
        justiceLeague.setLocation(tacoBell);

        justiceLeague = organizationDao.addOrganization(justiceLeague);
        
        List<Organization> organizations = new ArrayList();
        
        
        organizations.add(crimeFightersUnited);
        organizations.add(justiceLeague);
        
        List<Organization> result = organizationDao.getAllOrganizations();
        
        assertEquals(organizations, result);
        
        
    }
    
    @Test public void testUpdateOrganization() {
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

        Organization crimeFightersUnited = new Organization();

        crimeFightersUnited.setOrganizationName("Crime Fighters United");
        crimeFightersUnited.setOrganizationDescription("Hidden under the Mall of America");
        crimeFightersUnited.setOrganizationEmail("emailus@nowhere");
        crimeFightersUnited.setOrganizationPhone("5555555");
        crimeFightersUnited.setLocation(theMallofAmerica);

        crimeFightersUnited = organizationDao.addOrganization(crimeFightersUnited);
        
        String oldDescription = crimeFightersUnited.getOrganizationDescription();
        String newDescription = "Bigger than you'd expect";
        
        crimeFightersUnited.setOrganizationDescription(newDescription);
        
        organizationDao.updateOrganization(crimeFightersUnited);
        
       Organization result = organizationDao.getOrganizationById(crimeFightersUnited.getOrganizationId());
        
        assertNotEquals(result.getOrganizationDescription(), oldDescription);
        assertEquals(result.getOrganizationDescription(), newDescription);
        
       
    }
    
    @Test public void testDeleteOrganizationById() {
        
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

        Organization crimeFightersUnited = new Organization();

        crimeFightersUnited.setOrganizationName("Crime Fighters United");
        crimeFightersUnited.setOrganizationDescription("Hidden under the Mall of America");
        crimeFightersUnited.setOrganizationEmail("emailus@nowhere");
        crimeFightersUnited.setOrganizationPhone("5555555");
        crimeFightersUnited.setLocation(theMallofAmerica);

        crimeFightersUnited = organizationDao.addOrganization(crimeFightersUnited);
        
        organizationDao.deleteOrganizationById(crimeFightersUnited.getOrganizationId());
        
        assertNull(organizationDao.getOrganizationById(crimeFightersUnited.getOrganizationId()));
    }
   
}

    