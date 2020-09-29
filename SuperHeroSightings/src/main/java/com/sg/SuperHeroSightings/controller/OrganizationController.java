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
import java.math.BigDecimal;
import java.util.ArrayList;
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

public class OrganizationController {

    @Autowired
    LocationDao locationDao;

    @Autowired
    OrganizationDao organizationDao;

    @Autowired
    HeroDao heroDao;

    @GetMapping("organizations")
    public String displayOrganizations(Model model) {
        List<Organization> organizations = organizationDao.getAllOrganizations();
        model.addAttribute("organizations", organizations);
        return "organizations";
    }

    @PostMapping("addOrganization")
    public String addOrganization(HttpServletRequest request) {

        int id = Integer.parseInt(request.getParameter("id"));

        String[] heroIDs = request.getParameterValues("heroID");
        List<Hero> heroes = new ArrayList<>();
        if (heroIDs != null) {
            for (String heroID : heroIDs) {
                heroes.add(heroDao.getHeroById(Integer.parseInt(heroID)));
            }
        }

        Location location = locationDao.getLocationById(Integer.parseInt(request.getParameter("locationID")));
        String organizationName = request.getParameter("organizationName");
        String organizationDescription = request.getParameter("organizationDescription");
        String organizationEmail = request.getParameter("organizationEmail");
        String organizationPhone = request.getParameter("organizationPhone");

        Organization organization = new Organization();
        
        organization.setHeroes(heroes);
        organization.setLocation(location);
        organization.setOrganizationName(organizationName);
        organization.setOrganizationDescription(organizationDescription);
        organization.setOrganizationEmail(organizationEmail);
        organization.setOrganizationPhone(organizationPhone);
        
        organizationDao.addOrganization(organization);

        return "redirect:/organizations";
    }

    @GetMapping("deleteOrganization")
    public String deleteOrganization(HttpServletRequest request) {

        int id = Integer.parseInt(request.getParameter("id"));
        organizationDao.deleteOrganizationById(id);
        return "redirect:/organizations";

    }

    @GetMapping("editOrganization")
    public String editOrganization(HttpServletRequest request, Model model) {

        int id = Integer.parseInt(request.getParameter("id"));
        Organization organization = organizationDao.getOrganizationById(id);
        model.addAttribute("organization", organization);
        return "editOrganization";
    }

    @PostMapping("editOrganization")
    public String performEditOrganization(HttpServletRequest request) {

        int id = Integer.parseInt(request.getParameter("id"));
        Organization organization = organizationDao.getOrganizationById(id);

        String[] heroIDs = request.getParameterValues("heroID");
        List<Hero> heroes = new ArrayList<>();
        if (heroIDs != null) {
            for (String heroID : heroIDs) {
                heroes.add(heroDao.getHeroById(Integer.parseInt(heroID)));
            }
        }

        Location location = locationDao.getLocationById(Integer.parseInt(request.getParameter("locationID")));
        String organizationName = request.getParameter("organizationName");
        String organizationDescription = request.getParameter("organizationDescription");
        String organizationEmail = request.getParameter("organizationEmail");
        String organizationPhone = request.getParameter("organizationPhone");

        organization.setHeroes(heroes);
        organization.setLocation(location);
        organization.setOrganizationName(organizationName);
        organization.setOrganizationDescription(organizationDescription);
        organization.setOrganizationEmail(organizationEmail);
        organization.setOrganizationPhone(organizationPhone);
        
        organizationDao.updateOrganization(organization);
        

        return "redirect:/organizations";
    }
}