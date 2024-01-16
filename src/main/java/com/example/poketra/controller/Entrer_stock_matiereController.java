package com.example.poketra.controller;

import com.example.poketra.model.Entrer_stock_matiere;
import com.example.poketra.model.Matiere;
import com.example.poketra.repository.Entrer_stock_matiereRepository;
import com.example.poketra.repository.MatiereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.List;

@Controller
@RequestMapping("/entrer")
public class Entrer_stock_matiereController {
    @Autowired
    MatiereRepository matiereRepository;
    @Autowired
    Entrer_stock_matiereRepository entrerStockMatiereRepository;
    @GetMapping("/affins")
    public String affins(Model model) {
        List<Matiere> matiereList = matiereRepository.findAll();
        model.addAttribute("listmatiere",matiereList);

        return "stock_insert";
    }

    @PostMapping("/insert")
    public String insert(Model model, @RequestParam("matiere") int matiere, @RequestParam("unite") double unite, @RequestParam("date") String date) {
        try {
            Entrer_stock_matiere e = new Entrer_stock_matiere();
            e.setId_matiere(matiere);
            e.setQuantite(unite);
            e.setDate_entrer(Date.valueOf(date));

            entrerStockMatiereRepository.save(e);

            return "redirect:/entrer/affins";
        } catch (Exception e) {
            model.addAttribute("message",e.getMessage());

            return "erreur";
        }

    }
}
