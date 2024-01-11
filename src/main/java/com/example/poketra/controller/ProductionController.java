package com.example.poketra.controller;

import com.example.poketra.Service.ProductionService;
import com.example.poketra.model.Matiere;
import com.example.poketra.model.Poketra;
import com.example.poketra.model.Production;
import com.example.poketra.model.Taille;
import com.example.poketra.repository.MatiereRepository;
import com.example.poketra.repository.PoketraRepository;
import com.example.poketra.repository.ProductionRepository;
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
@RequestMapping("/production")
public class ProductionController {
    @Autowired
    PoketraRepository poketraRepository;
    @Autowired
    MatiereRepository matiereRepository;
    @Autowired
    TailleRepository tailleRepository;
    @Autowired
    ProductionRepository productionRepository;
    @Autowired
    ProductionService productionService;

    @GetMapping("/page")
    public String page(Model model) {
        List<Poketra> poketraList = poketraRepository.findAll();
        List<Matiere> matiereList = matiereRepository.findAll();
        List<Taille> tailleList = tailleRepository.findAll();

        model.addAttribute("listpoketra",poketraList);
        model.addAttribute("listmatiere",matiereList);
        model.addAttribute("listtaille",tailleList);

        return "production_insert";
    }

    @PostMapping("/insert")
    public String insert(Model model, @RequestParam("poketra") Integer idpoketra,@RequestParam("matiere") Integer idmatiere,@RequestParam("taille") Integer idtaille,@RequestParam("quantite") double quantite) {
        try {
            Production production = new Production();
            production.setId_poketra(idpoketra);
            production.setId_matiere(idmatiere);
            production.setId_taille(idtaille);
            production.setQuantite(quantite);

            productionRepository.save(production);
            return "redirect:/production/page";
        } catch (Exception e) {
            model.addAttribute("message",e.getMessage());
            return "erreur";
        }
    }

    @PostMapping("/matiere")
    public String matiere(Model model,@RequestParam("Matiere") Integer idmatiere) {
        Matiere m = matiereRepository.findById(idmatiere).get();
        List<Production> productionList = productionRepository.getAllProductionBymatiere(idmatiere);
        List<Production> reel = productionService.initProduction(productionList);
        model.addAttribute("listproduction",reel);
        model.addAttribute("matiere",m);

        return "resultat";
    }
}
