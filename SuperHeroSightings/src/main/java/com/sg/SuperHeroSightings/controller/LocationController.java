/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.SuperHeroSightings.controller;

import com.sg.SuperHeroSightings.dao.LocationDao;
import com.sg.SuperHeroSightings.dto.Location;
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

    @GetMapping("locations")
    public String displayLocations(Model model) {
        List<Location> locations = locationDao.getAllLocations();
        model.addAttribute("locations", locations);
        return "locations";
    }

    @PostMapping("addLocation")
    public String addLocation(HttpServletRequest request) {

        String locationName = request.getParameter("locationName");
        String description = request.getParameter("description");
        String address = request.getParameter("address");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
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
        locationDao.deleteLocationById(id);
        return "redirect:/locations";

    }

    @GetMapping("editLocation")
    public String editLocation(HttpServletRequest request, Model model) {

        int id = Integer.parseInt(request.getParameter("id"));
        Location location = locationDao.getLocationById(id);
        model.addAttribute("location", location);
        return "editLocation";
    }

    @PostMapping("editLocation")
    public String performEditLocation(HttpServletRequest request) {

        int id = Integer.parseInt(request.getParameter("id"));
        Location location = locationDao.getLocationById(id);

        String locationName = request.getParameter("locationName");
        String description = request.getParameter("description");
        String address = request.getParameter("address");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
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