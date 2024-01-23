package com.example.poketra.controller;

import com.example.poketra.Service.MpiasaService;
import com.example.poketra.model.Mpiasa;
import com.example.poketra.repository.MpiasaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/mpiasa")
public class MpiasaController {
    @Autowired
    MpiasaService mpiasaService;
    @Autowired
    MpiasaRepository mpiasaRepository;
    @GetMapping("/aff")
    public String aff(Model model) {
        return "filtre_insert";
    }

    @GetMapping("/resultat")
    public String resultat(Model model,@RequestParam("date") String date) {
        List<Mpiasa> mpiasaList = mpiasaService.traitement_tauxHoraire(date);
        model.addAttribute("mpiasalist",mpiasaList);

        return "status_list";
    }

    @GetMapping("/status")
    public String status(Model model) {

        return "status_update";
    }

    @GetMapping("/affrecrut")
    public String affrecruter(Model model) {


        return "mpiasa_insert";
    }
    @PostMapping("/insertrecrut")
    public String insertrecrut(Model model) {



        return "Redirect:/mpiasa/mpiasa_insert";
    }

}
