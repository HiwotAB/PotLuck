package com.hiwotab.potluck.controllers;

import com.hiwotab.potluck.Models.PotLuck;
import com.hiwotab.potluck.repositories.PotLuckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class PotController {
    @Autowired
    PotLuckRepository potLuckRepository;

    @GetMapping("/")
    public String homePageD() {
        return "homePage";
    }

    @GetMapping("/homePage")
    public String homePage() {
        return "homePage";
    }

    @GetMapping("/addPotLuck")
    public String addPotLuckInfo(Model model) {
        model.addAttribute("newPot", new PotLuck());
        return "addPotLuck";
    }

    @PostMapping("/addPotLuck")
    public String addPotLuckInfo(@Valid @ModelAttribute("newPot") PotLuck pot, BindingResult result) {
        if (result.hasErrors()) {
            return "addPotLuck";
        }
        potLuckRepository.save(pot);
        return "dispPotLuck";
    }
    @RequestMapping("/update/{id}")
    public String updateCourse(@PathVariable("id") long id, Model model){
        model.addAttribute("newPot", potLuckRepository.findOne(id));
        return "addPotLuck";
    }

    @RequestMapping("/delete/{id}")
    public String delCourse(@PathVariable("id") long id){
        potLuckRepository.delete(id);
        return "redirect:/ListPotLuckInfo";
    }
    @GetMapping("/ListPotLuckInfo")
    public String viewPotLuckInfo(Model model) {
        Iterable<PotLuck> potList = potLuckRepository.findAll();
        model.addAttribute("newPots", potList);
        return "ListPotLuckInfo";
    }

    @GetMapping("/searchBYFName")
    public String searchFNameMethod(Model model){
        model.addAttribute("searchFname",new PotLuck());
        return "searchBYFName";
    }

    @PostMapping("/searchBYFName")
    public String searchFNameMethod(@ModelAttribute("searchFname") PotLuck potLuck, Model model){
        Iterable<PotLuck>  listFName= potLuckRepository.findAllByFirstname(potLuck.getFirstname());
        model.addAttribute("listName",listFName);

        return "dispFName";
    }

    @GetMapping("/searchByDish")
    public String searchDishMethod(Model model){
        model.addAttribute("searchDish",new PotLuck());
        return "searchByDish";
    }
    @PostMapping("/searchByDish")
    public String searchDishMethod(@ModelAttribute("searchDish") PotLuck potLuck, Model model)
    {
        Iterable<PotLuck>  listDish= potLuckRepository.findAllByDish(potLuck.getDish());
        model.addAttribute("listDish",listDish);
        return "dispDish";
    }


}
