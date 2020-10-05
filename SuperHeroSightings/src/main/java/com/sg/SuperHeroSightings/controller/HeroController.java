
package com.sg.SuperHeroSightings.controller;

import com.sg.SuperHeroSightings.dao.HeroDao;
import com.sg.SuperHeroSightings.dao.OrganizationDao;
import com.sg.SuperHeroSightings.dao.SuperpowerDao;
import com.sg.SuperHeroSightings.dto.Hero;
import com.sg.SuperHeroSightings.dto.Organization;
import com.sg.SuperHeroSightings.dto.Superpower;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;




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

        
       String superPowerIDAsString = request.getParameter("superPowerIdForAddHero");
       int superPowerID=Integer.parseInt(superPowerIDAsString);
        Superpower superpower = superpowerDao.getSuperpowerById(superPowerID);

        
        String[] stringOrganizationIDs = request.getParameterValues("organizationIDForAddHero");
        

        List<Integer> organizationIds = new ArrayList<>();
        
            for (String organizationID : stringOrganizationIDs) {
                int i= Integer.parseInt(organizationID);
                organizationIds.add(i);
            
        }

        Hero hero = new Hero();
        hero.setHeroName(heroName);
        hero.setHeroDescription(heroDescription);
        hero.setSuperPower(superpower);
        hero.setOrganizationIds(organizationIds);

        heroDao.addHero(hero);
        return "redirect:/heroes";
    }
    
    
    @GetMapping("getHero")
    public String editHero(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("id"));
        Hero hero = heroDao.getHeroById(id);
        model.addAttribute("hero", hero);
        return "editHero";
    }
    
    
   

    @GetMapping("deleteHero")
    public String deleteHero(HttpServletRequest request) {

        int id = Integer.parseInt(request.getParameter("id"));
        heroDao.deleteHeroById(id);
        return "redirect:/heroes";
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
        //hero.setOrganizations(organizations);

        heroDao.updateHero(hero);
        return "redirect:/heroes";
    }
}