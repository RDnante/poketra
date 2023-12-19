package com.example.poketra.controller;

import com.example.poketra.model.Type;
import com.example.poketra.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/type")
public class TypeController {
    @Autowired
    TypeRepository typeRepository;

    @GetMapping("/page")
    public String page(Model model) {
        return "type_insert";
    }

    @PostMapping("/insert")
    public String insert(Model model, @RequestParam("type") String type) {
        Type type1 = new Type();
        type1.setNom(type);
        typeRepository.save(type1);

        return "type_insert";
    }
}
