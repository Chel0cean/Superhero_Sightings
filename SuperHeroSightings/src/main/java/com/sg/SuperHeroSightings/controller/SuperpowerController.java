package com.sg.SuperHeroSightings.controller;

import com.sg.SuperHeroSightings.dao.SuperpowerDao;
import com.sg.SuperHeroSightings.dto.Superpower;
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
public class SuperpowerController {

    @Autowired
    SuperpowerDao superpowerDao;

    Set<ConstraintViolation<Superpower>> violations = new HashSet<>();

    @GetMapping("superpowers")
    public String displaySuperpowers(Model model) {
        List<Superpower> superpowers = superpowerDao.getAllSuperpowers();
        model.addAttribute("superpowers", superpowers);
        model.addAttribute("errors", violations);
        return "superpowers";
    }

    @PostMapping("addSuperpower")
    public String addSuperpower(HttpServletRequest request) {
        String power = request.getParameter("superPowerName");

        Superpower superpower = new Superpower(power);
        superpowerDao.addSuperpower(superpower);
        return "redirect:/superpowers";
    }

    @GetMapping("deleteSuperpower")
    public String deleteSuperpower(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        superpowerDao.deleteSuperpowerById(id);
        return "redirect:/superpowers";
    }
//
//    @GetMapping("editSuperpower")
//    public String editSuperPower(HttpServletRequest request, Model model) {
//        int id = Integer.parseInt(request.getParameter("id"));
//        Superpower superpower = superpowerDao.getSuperpowerById(id);
//        model.addAttribute("superpower", superpower);
//        return "editSuperpower";
//    }

    @PostMapping("editSuperpower")
    public String performEditSuperpower(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("superPowerId"));
        Superpower superpower = superpowerDao.getSuperpowerById(id);
        String powerName = request.getParameter("superPowerName");

        superpower.setSuperPowerName(powerName);

        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(superpower);

        if (violations.isEmpty()) {
            superpowerDao.updateSuperpower(superpower);
        }

        return "redirect:/superpowers";
    }
}
