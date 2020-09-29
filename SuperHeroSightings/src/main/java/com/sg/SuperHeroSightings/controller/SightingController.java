/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.SuperHeroSightings.controller;

import com.sg.SuperHeroSightings.dao.HeroDao;
import com.sg.SuperHeroSightings.dao.LocationDao;
import com.sg.SuperHeroSightings.dao.OrganizationDao;
import com.sg.SuperHeroSightings.dao.SightingDao;
import com.sg.SuperHeroSightings.dto.Hero;
import com.sg.SuperHeroSightings.dto.Location;
import com.sg.SuperHeroSightings.dto.Organization;
import com.sg.SuperHeroSightings.dto.Sighting;
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
public class SightingController {

    @Autowired
    SightingDao sightingDao;

    @Autowired
    LocationDao locationDao;

    @Autowired
    HeroDao heroDao;

    @Autowired
    OrganizationDao organizationDao;

    @GetMapping("sightings")
    public String displaySightings(Model model) {
        List<Sighting> sightings = sightingDao.getAllSightings();
        List<Hero> heroes = heroDao.getAllHeroes();
        List<Location> locations = locationDao.getAllLocations();
        List<Organization> organizations = organizationDao.getAllOrganizations();
        model.addAttribute("sightings", sightings);
        model.addAttribute("heroes", heroes);
        model.addAttribute("locations", locations);
        model.addAttribute("organizations", organizations);
        return "sightings";
    }

    //VERY CONFUSED ABOUT ADD SIGHTING
    @PostMapping("addSighting")
    public String addSighting(HttpServletRequest request) {
        String date = request.getParameter("date");
        int heroID = Integer.parseInt(request.getParameter("heroID"));
        int locationID = Integer.parseInt(request.getParameter("locationID"));

        Sighting sighting = new Sighting();
        sighting.setLocation(locationDao.getLocationById(heroID));
        //sighting.setDate();
        sighting.setHero(heroDao.getHeroById(locationID));

        sightingDao.addSighting(sighting);
        return "redirect:/sightings";
    }

    @GetMapping("deleteSighting")
    public String deleteSighting(HttpServletRequest request) {

        int id = Integer.parseInt(request.getParameter("id"));
        sightingDao.deleteSightingById(id);
        return "redirect:/sightings";

    }

    @GetMapping("editSighting")
    public String editSighting(HttpServletRequest request, Model model) {

        int id = Integer.parseInt(request.getParameter("id"));
        Sighting sighting = sightingDao.getSightingById(id);
        
        List<Hero> heroes = heroDao.getAllHeroes();
        List<Location> locations = locationDao.getAllLocations();
        List<Organization> organizations = organizationDao.getAllOrganizations();
        
        model.addAttribute("heroes", heroes);
        model.addAttribute("locations", locations);
        model.addAttribute("organizations", organizations);
        model.addAttribute("sighting", sighting);
        return "editSighting";
    }

    
    //NEED TO FINISH AS WELL
    @PostMapping("editSighting")
    public String performEditSighting(HttpServletRequest request) {

        int id = Integer.parseInt(request.getParameter("id"));
        Sighting sighting = sightingDao.getSightingById(id);

        sightingDao.updateSighting(sighting);

        return "redirect:/sightings";
    }
}