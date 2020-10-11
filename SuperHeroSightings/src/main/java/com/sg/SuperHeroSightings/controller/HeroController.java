
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
        for(Hero hero:heroes){
            List <Organization> orgs=organizationDao.getOrganizationsByHero(hero);
            heroDao.associateOrgsForHero(hero, orgs);
        }
        
        List<Superpower> superpowers = superpowerDao.getAllSuperpowers();
        List<Organization> organizations = organizationDao.getAllOrganizations();

        model.addAttribute("heroes", heroes);
        model.addAttribute("superpowers", superpowers);
        model.addAttribute("organizations", organizations);

        return "heroes";
    }
    
      @GetMapping("hero")
    public String getHero(HttpServletRequest request, Model model) {
        String idAsString=request.getParameter("id");
        int id = Integer.parseInt(idAsString);
        Hero hero=heroDao.getHeroById(id);
         List<Superpower> superpowers = superpowerDao.getAllSuperpowers();
        List<Organization> organizations = organizationDao.getAllOrganizations();
         List <Organization> orgs=organizationDao.getOrganizationsByHero(hero);
            heroDao.associateOrgsForHero(hero, orgs);
            model.addAttribute(hero);
             model.addAttribute("superpowers", superpowers);
        model.addAttribute("organizations", organizations);
            return "hero";
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
        heroDao.addHero(hero);
        heroDao.insertHeroOrganization(hero, organizationIds);
        return "redirect:/heroes";
    }
    

    
    @GetMapping("deleteHero")
    public String deleteHero(HttpServletRequest request) {

        int id = Integer.parseInt(request.getParameter("id"));
        heroDao.deleteHeroById(id);
        return "redirect:/heroes";
    }


    
    @PostMapping("editHero")
    public String performEditHero(HttpServletRequest request) {

      int heroId=Integer.parseInt(request.getParameter("heroIdEdit"));
      
        String heroName = request.getParameter("heroNameEdit");
        String heroDescription = request.getParameter("heroDescriptionEdit");

        int superPowerID = Integer.parseInt(request.getParameter("superPowerIDEdit"));
        Superpower superpower = new Superpower(superPowerID);

        String[] organizationIDsString = request.getParameterValues("organizationIDForEditHero");

        List<Integer> organizationIDs = new ArrayList<>();
        
            for (String organizationID : organizationIDsString) {
                organizationIDs.add(Integer.parseInt(organizationID));
            
        }

        Hero hero = heroDao.getHeroById(heroId);
        hero.setHeroName(heroName);
        hero.setHeroDescription(heroDescription);
        hero.setSuperPower(superpower);
        heroDao.updateHero(hero);
        heroDao.insertHeroOrganization(hero, organizationIDs);
        return "redirect:/heroes";
    }

   
    
      @GetMapping("searchHeroesBySuperpower")
    public String searchHeroesBySuperpower(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("superPowerId"));
        
        Superpower superpower = superpowerDao.getSuperpowerById(id);
        List <Hero> heroes = heroDao.getHeroesBySuperpower(superpower);
          for(Hero hero:heroes){
            List <Organization> orgs=organizationDao.getOrganizationsByHero(hero);
            heroDao.associateOrgsForHero(hero, orgs);
        }
        
        List<Superpower> superpowers = superpowerDao.getAllSuperpowers();
        List<Organization> organizations = organizationDao.getAllOrganizations();
     String ability = superpower.getSuperPowerName();
         model.addAttribute("superpower", superpower);
         model.addAttribute("superpowers", superpowers);
         model.addAttribute("organizations", organizations);
         model.addAttribute("heroes", heroes);
         model.addAttribute("ability", ability);
        return "searchHeroesBySuperpower";   
 
       
    }
    

    
       
      @GetMapping("searchHeroesByOrganization")
    public String searchHeroesByOrganization(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("organizationId"));
        Organization organization = organizationDao.getOrganizationById(id);
        List <Hero> heroes = heroDao.getHeroesByOrganization(organization);
          for(Hero hero:heroes){
            List <Organization> orgs=organizationDao.getOrganizationsByHero(hero);
            heroDao.associateOrgsForHero(hero, orgs);
        }
        
         List<Superpower> superpowers = superpowerDao.getAllSuperpowers();
        List<Organization> organizations = organizationDao.getAllOrganizations();
        model.addAttribute("heroes", heroes);
        model.addAttribute("superpowers", superpowers);
        model.addAttribute("organizations", organizations);
        model.addAttribute("organization", organization);
        return "searchHeroesByOrganization";
    }


    

}