package com.sg.SuperHeroSightings.controller;

import com.sg.SuperHeroSightings.dao.HeroDao;
import com.sg.SuperHeroSightings.dao.LocationDao;
import com.sg.SuperHeroSightings.dao.OrganizationDao;
import com.sg.SuperHeroSightings.dto.Hero;
import com.sg.SuperHeroSightings.dto.Location;
import com.sg.SuperHeroSightings.dto.Organization;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Chelsea, Karma, Mohammed, Patrick
 */
@Controller
public class LocationController {

    @Autowired
    LocationDao locationDao;

    @Autowired
    OrganizationDao organizationDao;

    @Autowired
    HeroDao heroDao;

    Set<ConstraintViolation<Location>> violations = new HashSet<>();

    @GetMapping("locations")
    public String displayLocations(Model model) {
        List<Location> locations = locationDao.getAllLocations();
        model.addAttribute("locations", locations);
        model.addAttribute("errors", violations);
        return "locations";
    }

    @GetMapping("location")
    public String getLocation(HttpServletRequest request, Model model) {
        String idAsString = request.getParameter("id");
        int id = Integer.parseInt(idAsString);
        Location location = locationDao.getLocationById(id);
        model.addAttribute(location);
        model.addAttribute("errors", violations);
        return "location";
    }

    @PostMapping("addLocation")
    public String addLocation(HttpServletRequest request) {
        Location location = new Location();
        BigDecimal latitude = null;
        BigDecimal longitude = null;

        String locationName = request.getParameter("locationName");
        String description = request.getParameter("locationDescription");
        String address = request.getParameter("locationAddress");
        String city = request.getParameter("locationCity");
        String state = request.getParameter("locationState");
        String country = request.getParameter("country");
        String zipCode = request.getParameter("zipCode");
        
if (!request.getParameter("latitude").isEmpty()) {
            latitude = new BigDecimal(request.getParameter("latitude"));
        }
        if (!request.getParameter("longitude").isEmpty()) {
            longitude = new BigDecimal(request.getParameter("longitude"));
        }
        location.setLocationName(locationName);
        location.setLocationDescription(description);
        location.setLocationAddress(address);
        location.setLocationCity(city);
        location.setLocationState(state);
        location.setCountry(country);
        location.setZipCode(zipCode);
        location.setLatitude(latitude);
        location.setLongitude(longitude);

        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(location);

        if (violations.isEmpty()) {
            locationDao.addLocation(location);
        }

        return "redirect:/locations";

    }

    @GetMapping("deleteLocation")
    public String deleteLocation(HttpServletRequest request) {

        int id = Integer.parseInt(request.getParameter("id"));
        locationCheckById(id);
        locationDao.deleteLocationById(id);
        return "redirect:/locations";

    }

    //Need to first retrieve an organization at that location (if there is one). then take that organization and get all heroes that belong to it. 
    //then if any heroes.getorganizationIDs list size = 1 (meanign they only belong to that one organization that is about to be deleted) delete that/those hero(es)
    private void locationCheckById(int id) {
        Organization organization = organizationDao.getOrganizationById(id);
        if (organization != null) {
            List<Hero> heroes = heroDao.getHeroesByOrganization(organization);
            if (heroes != null) {
                for (Hero hero : heroes) {
                    List<Organization> orgs = organizationDao.getOrganizationsByHero(hero);
                    heroDao.associateOrgsForHero(hero, orgs);
                    if (hero.getOrganizations().size() == 1) {
                        heroDao.deleteHeroById(hero.getHeroId());
                    }
                }
            }
        }
    }

    @PostMapping("editLocation")
    public String performEditLocation(HttpServletRequest request) {

        int id = Integer.parseInt(request.getParameter("locationId"));

        Location location = locationDao.getLocationById(id);
        BigDecimal latitude = null;
        BigDecimal longitude = null;

        String locationName = request.getParameter("locationName");
        String description = request.getParameter("locationDescription");
        String address = request.getParameter("locationAddress");
        String city = request.getParameter("locationCity");
        String state = request.getParameter("locationState");
        String country = request.getParameter("country");
        String zipCode = request.getParameter("zipCode");

        if (!request.getParameter("latitude").isEmpty()) {
            latitude = new BigDecimal(request.getParameter("latitude"));
        }
        if (!request.getParameter("longitude").isEmpty()) {
            longitude = new BigDecimal(request.getParameter("longitude"));
        }

        location.setLocationName(locationName);
        location.setLocationDescription(description);
        location.setLocationAddress(address);
        location.setLocationCity(city);
        location.setLocationState(state);
        location.setCountry(country);
        location.setZipCode(zipCode);
        location.setLatitude(latitude);
        location.setLongitude(longitude);

        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(location);

        if (violations.isEmpty()) {
            locationDao.updateLocation(location);
        }

        return "redirect:/locations";

    }
}
