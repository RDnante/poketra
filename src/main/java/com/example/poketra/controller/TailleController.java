package com.example.poketra.controller;

import com.example.poketra.model.Taille;
import com.example.poketra.repository.TailleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/taille")
public class TailleController {
    @Autowired
    TailleRepository tailleRepository;
    @GetMapping("/pageinsert")
    public String taille_insert(Model model) {
        return "taille_insert";
    }

    @PostMapping("/insert")
    public String insert(@RequestParam("nom")String nom,@RequestParam("unite")Double unite) {
        Taille taille = new Taille();
        taille.setNom(nom);
        taille.setUnite(unite);
        tailleRepository.save(taille);

        return "taille_insert";
    }
}
