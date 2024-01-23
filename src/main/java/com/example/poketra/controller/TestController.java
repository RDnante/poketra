package com.example.poketra.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/test")
public class TestController {
    @GetMapping("/acceuil")
    public String acceuil(Model model) {

        return "exemple_acceuil";
    }

    @GetMapping("/formulaire")
    public String header(Model model) {
        return "exemple_formulaire";
    }

    @GetMapping("/table")
    public String table(Model model) {
        return "exemple_tableau";
    }

    @GetMapping("/chart")
    public String chart(Model model) {
        try {
            List<String> m = Arrays.asList("January", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec");
            List<Integer> donnee = Arrays.asList(0,0,5,10,30,0,0,12,40,20,10,2);
            ObjectMapper objectMapper = new ObjectMapper();
            String month = objectMapper.writeValueAsString(m);
            String d = objectMapper.writeValueAsString(donnee);

            model.addAttribute("mois",month);
            model.addAttribute("donnee",d);
        } catch (Exception e) {
            e.printStackTrace();
        }



        return "chart";
    }
}
