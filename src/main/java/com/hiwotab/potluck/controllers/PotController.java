package com.hiwotab.potluck.controllers;

import com.hiwotab.potluck.Models.Pot;
import com.hiwotab.potluck.repositories.PotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class PotController {
    @Autowired
    PotRepository potRepository;

    @GetMapping("/mainpage")
    public String loadForm() {
        return "mainpage";
    }

    @GetMapping("/addpot")
    public String addPot(Model model) {
        model.addAttribute("newPot", new Pot());
        return "addpot";
    }

    @PostMapping("/addpot")
    public String check(@Valid @ModelAttribute("newPot") Pot pot, BindingResult result) {
        if (result.hasErrors()) {
            return "addpot";
        }
        potRepository.save(pot);
        return "confirmPage";
    }

    @GetMapping("/viewpot")
    public String view(Model model) {
        Iterable<Pot> potList = potRepository.findAll();
        model.addAttribute("newPot", potList);
        return "viewpot";
    }

    @GetMapping("/searchname")
    public String searchByName(Model model) {
        model.addAttribute("searchNames", new Pot());
        return "searchname";
    }

    @PostMapping("/searchname")
    public String searchByName(Model model, @RequestParam("searchName") String firstnames) {
        if (firstnames != null) {
            Iterable<Pot> potList = potRepository.findAllByFirstname(firstnames);
            model.addAttribute("firstnames", potList);
        }
            return "resultName";
    }

    @GetMapping("/searchdish")
    public String searchByDish(Model model, @RequestParam("searchDish") String searchDish) {
        if (searchDish != null) {
            Iterable<Pot> potList = potRepository.findAllByDish(searchDish);
            model.addAttribute("searchDish", potList);
        }

        return "searchdish";
    }

}
