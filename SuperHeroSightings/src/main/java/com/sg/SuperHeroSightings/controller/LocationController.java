/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.SuperHeroSightings.controller;

import com.sg.SuperHeroSightings.dao.HeroDao;
import com.sg.SuperHeroSightings.dao.LocationDao;
import com.sg.SuperHeroSightings.dao.OrganizationDao;
import com.sg.SuperHeroSightings.dto.Hero;
import com.sg.SuperHeroSightings.dto.Location;
import com.sg.SuperHeroSightings.dto.Organization;
import com.sg.SuperHeroSightings.dto.Superpower;
import java.math.BigDecimal;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
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

    @GetMapping("locations")
    public String displayLocations(Model model) {
        List<Location> locations = locationDao.getAllLocations();
        model.addAttribute("locations", locations);
        return "locations";
    }

    @PostMapping("addLocation")
    public String addLocation(HttpServletRequest request) {

        String locationName = request.getParameter("locationName");
        String description = request.getParameter("locationDescription");
        String address = request.getParameter("locationAddress");
        String city = request.getParameter("locationCity");
        String state = request.getParameter("locationState");
        String country = request.getParameter("country");
        String zipCode = request.getParameter("zipCode");
        BigDecimal latitude = new BigDecimal(request.getParameter("latitude"));
        BigDecimal longitude = new BigDecimal(request.getParameter("longitude"));

        Location location = new Location();

        location.setLocationName(locationName);
        location.setLocationDescription(description);
        location.setLocationAddress(address);
        location.setLocationCity(city);
        location.setLocationState(state);
        location.setCountry(country);
        location.setZipCode(zipCode);
        location.setLatitude(latitude);
        location.setLongitude(longitude);

        locationDao.addLocation(location);
        return "redirect:/locations";
    }

    @GetMapping("deleteLocation")
    public String deleteLocation(HttpServletRequest request) {

        int id = Integer.parseInt(request.getParameter("id"));
//        locationCheckById(id);
        locationDao.deleteLocationById(id);
        return "redirect:/locations";

    }

    //Need to first retrieve an organization at that location (if there is one). then take that organization and get all heroes that belong to it. 
    //then if any heroes.getorganizationIDs list size = 1 (meanign they only belong to that one organization that is about to be deleted) delete that/those hero(es)

//    private void locationCheckById(int id) {
//        Organization organization = organizationDao.getOrganizationById(id);
//        if(organization != null) {
//            List<Hero> heroes = heroDao.getHeroesByOrganization(organization);
//            if(heroes != null){
//                for (Hero hero : heroes) {
//                    if(hero.getOrganizationIds().size() = 1) {
//                        heroDao.deleteHeroById(hero.getHeroId());
//                    }
//                }
//            }
//        }
//    }
    
    @GetMapping("editLocation")
    public String editLocation(HttpServletRequest request, Model model) {

        int id = Integer.parseInt(request.getParameter("id"));
        System.out.println("test");
        System.out.println("This id is" + id);
        Location location = locationDao.getLocationById(id);
        System.out.println("com.sg.SuperHeroSightings.controller.LocationController.editLocation()");
        model.addAttribute("location", location);
        return "editLocation";
    }

    @PostMapping("editLocation")
    public String performEditLocation(HttpServletRequest request) {

        int id = Integer.parseInt(request.getParameter("locationId"));
        System.out.println("This id is" + id);
        Location location = locationDao.getLocationById(id);
        System.out.println("com.sg.SuperHeroSightings.controller.LocationController.performEditLocation()");

        System.out.println(id);
        String locationName = request.getParameter("locationName");
        String description = request.getParameter("locationDescription");
        String address = request.getParameter("locationAddress");
        String city = request.getParameter("locationCity");
        String state = request.getParameter("locationState");
        String country = request.getParameter("country");
        String zipCode = request.getParameter("zipCode");
        BigDecimal latitude = new BigDecimal(request.getParameter("latitude"));
        BigDecimal longitude = new BigDecimal(request.getParameter("longitude"));

        location.setLocationName(locationName);
        location.setLocationDescription(description);
        location.setLocationAddress(address);
        location.setLocationCity(city);
        location.setLocationState(state);
        location.setCountry(country);
        location.setZipCode(zipCode);
        location.setLatitude(latitude);
        location.setLongitude(longitude);

        locationDao.updateLocation(location);

        return "redirect:/locations";
    }
}
