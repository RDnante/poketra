package com.example.poketra.controller;

import com.example.poketra.model.Poketra;
import com.example.poketra.model.Style;
import com.example.poketra.model.Type;
import com.example.poketra.repository.PoketraRepository;
import com.example.poketra.repository.StyleRepository;
import com.example.poketra.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/poketra")
public class PoketraController {
    @Autowired
    PoketraRepository poketraRepository;
    @Autowired
    StyleRepository styleRepository;
    @Autowired
    TypeRepository typeRepository;

    @GetMapping("/page")
    public String page(Model model) {
        List<Style> liststyStyles = styleRepository.findAll();
        List<Type> lisTypes = typeRepository.findAll();
        model.addAttribute("liststyle",liststyStyles);
        model.addAttribute("listtype",lisTypes);

        return "poketra_insert";
    }

    @PostMapping("/insert")
    public String insert(Model model, @RequestParam("style") Integer idstyle,@RequestParam("type") Integer idtype,@RequestParam("nom") String nom) {
        Poketra poketra = new Poketra();
        poketra.setId_style(idstyle);
        poketra.setId_type(idtype);
        poketra.setNom(nom);

        poketraRepository.save(poketra);

        return "poketra_insert";
    }
}
