/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.SuperHeroSightings.controller;

import com.sg.SuperHeroSightings.dao.HeroDao;
import com.sg.SuperHeroSightings.dao.OrganizationDao;
import com.sg.SuperHeroSightings.dao.SuperpowerDao;
import com.sg.SuperHeroSightings.dto.Hero;
import com.sg.SuperHeroSightings.dto.Location;
import com.sg.SuperHeroSightings.dto.Organization;
import com.sg.SuperHeroSightings.dto.Superpower;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Chelsea, Karma, Mohammed, Patrick
 */
@Controller
public class HeroController {

    @Autowired
    SuperpowerDao superpowerDao;

    @Autowired
    OrganizationDao organizationDao;

    @Autowired
    HeroDao heroDao;

    @GetMapping("heroes")
    public String displayHeroes(Model model) {
        List<Hero> heroes = heroDao.getAllHeroes();
        List<Superpower> superpowers = superpowerDao.getAllSuperpowers();
        List<Organization> organizations = organizationDao.getAllOrganizations();

        model.addAttribute("heroes", heroes);
        model.addAttribute("superpowers", superpowers);
        model.addAttribute("organizations", organizations);

        return "heroes";
    }

    @PostMapping("addHero")
    public String addHero(HttpServletRequest request) {

        String heroName = request.getParameter("heroName");
        String heroDescription = request.getParameter("heroDescription");

        //Should I be pulling the superpower from the dao?
        int superPowerID = Integer.parseInt(request.getParameter("superPowerID"));
        Superpower superpower = new Superpower(superPowerID);

        //Organization Handling?
        String[] organizationIDs = request.getParameterValues("organizationID");

        List<Organization> organizations = new ArrayList<>();
        if (organizationIDs != null) {
            for (String organizationID : organizationIDs) {
                organizations.add(organizationDao.getOrganizationById(Integer.parseInt(organizationID)));
            }
        }

        Hero hero = new Hero();
        hero.setHeroName(heroName);
        hero.setHeroDescription(heroDescription);
        hero.setSuperPower(superpower);
        hero.setOrganizations(organizations);

        heroDao.addHero(hero);
        return "redirect:/heroes";
    }

    @GetMapping("deleteHero")
    public String deleteHero(HttpServletRequest request) {

        int id = Integer.parseInt(request.getParameter("id"));
        heroDao.deleteHeroById(id);
        return "redirect:/heroes";
    }

    @GetMapping("editHero")
    public String editHero(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("id"));
        Hero hero = heroDao.getHeroById(id);
        model.addAttribute("hero", hero);
        return "editHero";
    }

    //USED SAME CODE AS ADDHERO WE CAN FIX OBV
    @PostMapping("editHero")
    public String performEditHero(HttpServletRequest request) {

        int id = Integer.parseInt(request.getParameter("id"));
        String heroName = request.getParameter("heroName");
        String heroDescription = request.getParameter("heroDescription");

        int superPowerID = Integer.parseInt(request.getParameter("superPowerID"));
        Superpower superpower = new Superpower(superPowerID);

        String[] organizationIDs = request.getParameterValues("organizationID");

        List<Organization> organizations = new ArrayList<>();
        if (organizationIDs != null) {
            for (String organizationID : organizationIDs) {
                organizations.add(organizationDao.getOrganizationById(Integer.parseInt(organizationID)));
            }
        }

        Hero hero = heroDao.getHeroById(id);
        hero.setHeroName(heroName);
        hero.setHeroDescription(heroDescription);
        hero.setSuperPower(superpower);
        hero.setOrganizations(organizations);

        heroDao.updateHero(hero);
        return "redirect:/heroes";
    }
}