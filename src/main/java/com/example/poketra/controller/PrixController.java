package com.example.poketra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/prix")
public class PrixController {
    @GetMapping("/prixinsert")
    public String insertPrix(Model model){
        return "prix_insert";
    }

}
