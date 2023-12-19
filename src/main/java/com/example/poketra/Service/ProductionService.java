package com.example.poketra.Service;

import com.example.poketra.model.Poketra;
import com.example.poketra.model.Production;
import com.example.poketra.repository.PoketraRepository;
import com.example.poketra.repository.StyleRepository;
import com.example.poketra.repository.TailleRepository;
import com.example.poketra.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductionService {
    @Autowired
    PoketraRepository poketraRepository;
    @Autowired
    TailleRepository tailleRepository;
    @Autowired
    TypeRepository typeRepository;
    @Autowired
    StyleRepository styleRepository;
    public List<Production> initProduction(List<Production> list) {
        List<Production> valiny = new ArrayList<Production>();
        for (Production p : list) {
            p.setPoketra(poketraRepository.findById(p.getId_poketra()).get());
            p.setTaille(tailleRepository.findById(p.getId_taille()).get());
            Poketra poketra = p.getPoketra();
            poketra.setType(typeRepository.findById(poketra.getId_type()).get());
            poketra.setStyle(styleRepository.findById(poketra.getId_style()).get());
            p.setPoketra(poketra);
            valiny.add(p);
        }

        return valiny;
    }
}
