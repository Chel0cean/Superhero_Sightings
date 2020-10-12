package com.sg.SuperHeroSightings.controller;

import com.sg.SuperHeroSightings.dao.HeroDao;
import com.sg.SuperHeroSightings.dao.LocationDao;
import com.sg.SuperHeroSightings.dao.OrganizationDao;
import com.sg.SuperHeroSightings.dto.Hero;
import com.sg.SuperHeroSightings.dto.Location;
import com.sg.SuperHeroSightings.dto.Organization;
import java.util.ArrayList;
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

public class OrganizationController {

    @Autowired
    LocationDao locationDao;

    @Autowired
    OrganizationDao organizationDao;

    @Autowired
    HeroDao heroDao;

    Set<ConstraintViolation<Organization>> violations = new HashSet<>();

    @GetMapping("organizations")
    public String displayOrganizations(Model model) {
        List<Organization> organizations = organizationDao.getAllOrganizations();
        List<Hero> heroes = heroDao.getAllHeroes();
        List<Location> locations = locationDao.getAllLocations();
        model.addAttribute("organizations", organizations);
        model.addAttribute("heroes", heroes);
        model.addAttribute("locations", locations);
        model.addAttribute("errors", violations);
        return "organizations";
    }

    @GetMapping("organization")
    public String getorganization(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("id"));
        Organization organization = organizationDao.getOrganizationById(id);
        List<Hero> heroes = heroDao.getAllHeroes();
        List<Location> locations = locationDao.getAllLocations();

        model.addAttribute("heroes", heroes);
        model.addAttribute("locations", locations);
        model.addAttribute(organization);
        model.addAttribute("errors", violations);
        return "organization";
    }

    @PostMapping("addOrganization")
    public String addOrganization(HttpServletRequest request) {
        List<Hero> heroes = new ArrayList<>();
        Organization organization = new Organization();

        String organizatonName = request.getParameter("organizationName");

        String organizationDescription = request.getParameter("organizationDescription");

        try {
            String organizationEmail = request.getParameter("organizationEmail");
            String organizationPhone = request.getParameter("organizationPhone");
            organization.setOrganizationEmail(organizationEmail);
            organization.setOrganizationPhone(organizationPhone);
        } catch (NullPointerException ex) {

        }

        String locationIDAsString = request.getParameter("locationIdForAddOrganization");
        int locationID = Integer.parseInt(locationIDAsString);
        Location location = locationDao.getLocationById(locationID);

        String[] heroIDs = request.getParameterValues("heroID");

        for (String heroID : heroIDs) {
            heroes.add(heroDao.getHeroById(Integer.parseInt(heroID)));
        }
        organization.setHeroes(heroes);

        organization.setLocation(location);
        organization.setOrganizationName(organizatonName);
        organization.setOrganizationDescription(organizationDescription);

        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(organization);

        if (violations.isEmpty()) {
            organizationDao.addOrganization(organization);

        }

        return "redirect:/organizations";
    }

    @GetMapping("deleteOrganization")
    public String deleteOrganization(HttpServletRequest request) {

        int id = Integer.parseInt(request.getParameter("id"));
        organizationDao.deleteOrganizationById(id);
        return "redirect:/organizations";

    }

    @PostMapping("editOrganization")
    public String performEditOrganization(HttpServletRequest request) {

        int id = Integer.parseInt(request.getParameter("organizationIdEdit"));
        Organization organization = organizationDao.getOrganizationById(id);

        String[] heroIDs = request.getParameterValues("heroIDForEditOrganization");
        List<Hero> heroes = new ArrayList<>();
        if (heroIDs != null) {
            for (String heroID : heroIDs) {
                heroes.add(heroDao.getHeroById(Integer.parseInt(heroID)));
            }
        }
        Location location = locationDao.getLocationById(Integer.parseInt(request.getParameter("locationIDEdit")));
        String organizationName = request.getParameter("organizationNameEdit");
        String organizationDescription = request.getParameter("organizationDescriptionEdit");
        try {
            String organizationEmail = request.getParameter("organizationEmailEdit");
            organization.setOrganizationEmail(organizationEmail);
        } catch (NullPointerException ex) {

        }
        try {
            String organizationPhone = request.getParameter("organizationPhoneEdit");
            organization.setOrganizationPhone(organizationPhone);
        } catch (NullPointerException ex) {

        }
        organization.setHeroes(heroes);
        organization.setLocation(location);
        organization.setOrganizationName(organizationName);
        organization.setOrganizationDescription(organizationDescription);

        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(organization);

        if (violations.isEmpty()) {
            organizationDao.updateOrganization(organization);
        }

        return "redirect:/organizations";

    }

    @GetMapping("searchOrganizationsByHero")
    public String searchOrganizationsByHero(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("heroId"));
        Hero hero = heroDao.getHeroById(id);
        List<Organization> organizations = organizationDao.getOrganizationsByHero(hero);
        List<Hero> heroes = heroDao.getAllHeroes();
        List<Location> locations = locationDao.getAllLocations();
        model.addAttribute("organizations", organizations);
        model.addAttribute("hero", hero);
        model.addAttribute("heroes", heroes);
        model.addAttribute("locations", locations);

        model.addAttribute("errors", violations);

        return "searchOrganizationsByHero";
    }

}
