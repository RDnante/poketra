package com.example.poketra.controller;

import com.example.poketra.Service.Sortie_stock_matiereService;
import com.example.poketra.Service.Stock_poketraService;
import com.example.poketra.model.*;
import com.example.poketra.repository.Etat_stock_poketraRepository;
import com.example.poketra.repository.PoketraRepository;
import com.example.poketra.repository.TailleRepository;
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
@RequestMapping("/fabrication")
public class FabricationController {
    @Autowired
    PoketraRepository poketraRepository;
    @Autowired
    TailleRepository tailleRepository;
    @Autowired
    Sortie_stock_matiereService sortieStockMatiereService;
    @Autowired
    Etat_stock_poketraRepository etatStockPoketraRepository;
    @GetMapping("/affins")
    public String affins(Model model) {
        List<Poketra> listpoketra = poketraRepository.findAll();
        List<Taille> tailleList = tailleRepository.findAll();

        model.addAttribute("listpoketra",listpoketra);
        model.addAttribute("listtaille",tailleList);

        return "poketra_fabrication";
    }

    @PostMapping("/insert")
    public String insert(Model model, @RequestParam("poketra")Integer poketra,@RequestParam("taille") Integer taille,@RequestParam("unite") Double unite,@RequestParam("date") String date) {
        try {
            List<Sortie_stock_matiere> list = sortieStockMatiereService.fabrication(poketra,taille,unite,date);
            sortieStockMatiereService.insert_fabrication(list);
            Etat_stock_poketra e = new Etat_stock_poketra();
            e.setId_poketra(poketra);
            e.setEntrer(unite.intValue());
            e.setDate(Date.valueOf(date));
            e.setSortie(0);

            etatStockPoketraRepository.save(e);


            return "redirect:/fabrication/affins";

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("message",e.getMessage());

            return "erreur";
        }
    }

    @GetMapping("/reste")
    public String reste(Model model) {
        try {
            Reste_stock_matiere resteStockMatiere = new Reste_stock_matiere();
            List<Reste_stock_matiere> list = resteStockMatiere.getAllReste(null);

            model.addAttribute("listreste",list);

            return "stock_list";
        }catch (Exception e) {
            model.addAttribute("message",e.getMessage());
            return "erreur";
        }

    }
}
