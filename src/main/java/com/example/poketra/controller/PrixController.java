package com.example.poketra.controller;

import com.example.poketra.model.Poketra;
import com.example.poketra.model.Production_prix_total;
import com.example.poketra.repository.PoketraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/prix")
public class PrixController {
    @Autowired
    PoketraRepository poketraRepository;
    @GetMapping("/prixinsert")
    public String insertPrix(Model model){
        return "prix_insert";
    }

    @GetMapping("/recherche")
    public String recherche(Model model, @RequestParam("prix_min")Double prix_min,@RequestParam("prix_max")Double prix_max) {
        try {
            Production_prix_total p = new Production_prix_total();
            List<Production_prix_total> list_idpoketra = p.getall(prix_min,prix_max);

            model.addAttribute("listpoketra",list_idpoketra);

            return "poketra_list";
        }catch (Exception e) {

            return "erreur";
        }
    }

}
