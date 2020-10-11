
package com.sg.SuperHeroSightings.controller;
import com.sg.SuperHeroSightings.dao.HeroDao;
import com.sg.SuperHeroSightings.dao.LocationDao;
import com.sg.SuperHeroSightings.dao.SightingDao;
import com.sg.SuperHeroSightings.dto.Hero;
import com.sg.SuperHeroSightings.dto.Location;
import com.sg.SuperHeroSightings.dto.Sighting;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    
    
    @GetMapping("sightings")
    public String displaySightings(Model model) {
        List<Sighting> sightings = sightingDao.getAllSightings();
        List<Hero> heroes = heroDao.getAllHeroes();
        List<Location> locations = locationDao.getAllLocations();
        model.addAttribute("sightings", sightings);
        model.addAttribute("heroes", heroes);
        model.addAttribute("locations", locations);
        return "sightings";
    }
    
       @GetMapping("index")
    public String loadIndex(Model model) {
        List<Sighting> sightings = sightingDao.getTopTenSightings();
        List<Hero> heroes = heroDao.getAllHeroes();
        List<Location> locations = locationDao.getAllLocations();
        model.addAttribute("sightings", sightings);
        model.addAttribute("heroes", heroes);
        model.addAttribute("locations", locations);
        return "index";
    }
    
    
      @GetMapping("sighting")
    public String getSighting(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("id"));
        Sighting sighting = sightingDao.getSightingById(id);

        model.addAttribute(sighting);
        return "sighting";
    }
    
    @PostMapping("addSighting")
    public String addSighting(HttpServletRequest request) {
        LocalDate date = LocalDate.parse(request.getParameter("date"));
        int heroID = Integer.parseInt(request.getParameter("heroId"));
        int locationID = Integer.parseInt(request.getParameter("locationId"));
        Sighting sighting = new Sighting();
        sighting.setLocation(locationDao.getLocationById(locationID));
        sighting.setDate(date);
        sighting.setHero(heroDao.getHeroById(heroID));
        sightingDao.addSighting(sighting);
        return "redirect:/sightings";
    }
    @GetMapping("deleteSighting")
    public String deleteSighting(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        sightingDao.deleteSightingById(id);
        return "redirect:/sightings";
    }
    @PostMapping("editSighting")
    public String performEditSighting(HttpServletRequest request) {
        LocalDate date = LocalDate.parse(request.getParameter("dateForEdit"));
        int heroID = Integer.parseInt(request.getParameter("heroIdEdit"));
        int locationID = Integer.parseInt(request.getParameter("locationIdEdit"));
        int sightingID = Integer.parseInt(request.getParameter("sightingIdEdit"));
        Sighting sighting = new Sighting();
        sighting.setLocation(locationDao.getLocationById(locationID));
        sighting.setDate(date);
        sighting.setHero(heroDao.getHeroById(heroID));
        sighting.setSightingId(sightingID);
        sightingDao.updateSighting(sighting);
        return "redirect:/sightings";
    }
    @GetMapping("searchSightingsByLocation")
    public String searchSightingsByLocation(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("locationId"));
        Location location = locationDao.getLocationById(id);
        List<Sighting> sightings = sightingDao.getSightingsAtLocation(location);
        model.addAttribute("location", location);
        model.addAttribute("sightings", sightings);
        return "searchSightingsByLocation";
    }
    @GetMapping("searchSightingsByHero")
    public String searchSightingsByHero(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("heroId"));
        Hero hero = heroDao.getHeroById(id);
        List<Sighting> sightings = sightingDao.getSightingsOfHero(hero);
        model.addAttribute("hero", hero);
        model.addAttribute("sightings", sightings);
        return "searchSightingsByHero";
    }
    @GetMapping("searchSightingsByDate")
    public String searchSightingsByDate(HttpServletRequest request, Model model) {
        LocalDate dateAsLocalDate = LocalDate.parse(request.getParameter("date"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-YYYY");
        String date=formatter.format(dateAsLocalDate);
        List<Sighting> sightings = sightingDao.getSightingsFromDate(dateAsLocalDate);
        model.addAttribute("date", date);
        model.addAttribute("sightings", sightings);
        return "searchSightingsByDate";
    }
    @GetMapping("topTenSightings") //Can be changed
    public String displayTopTenSightings(Model model) {
        List<Sighting> topTenSightings = sightingDao.getTopTenSightings();
        model.addAttribute("topTenSightings", topTenSightings);
        return "index"; //Can be changed
    }
}