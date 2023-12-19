package com.example.poketra.controller;


import com.example.poketra.model.Style;
import com.example.poketra.model.Type;
import com.example.poketra.repository.StyleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/style")
public class StyleController {
    @Autowired
    StyleRepository styleRepository;

    @GetMapping("/page")
    public String page(Model model) {
        return "style_insert";
    }

    @PostMapping("/insert")
    public String insert(Model model, @RequestParam("style") String style) {
        Style style1 = new Style();
        style1.setNom(style);
        styleRepository.save(style1);

        return "style_insert";
    }
}
