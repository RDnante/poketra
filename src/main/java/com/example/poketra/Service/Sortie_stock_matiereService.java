package com.example.poketra.Service;

import com.example.poketra.model.Poketra;
import com.example.poketra.model.Production;
import com.example.poketra.model.Reste_stock_matiere;
import com.example.poketra.model.Sortie_stock_matiere;
import com.example.poketra.repository.ProductionRepository;
import com.example.poketra.repository.Sortie_stock_matiereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class Sortie_stock_matiereService {
    @Autowired
    ProductionRepository productionRepository;
    @Autowired
    Sortie_stock_matiereRepository sortieStockMatiereRepository;

    public List<Sortie_stock_matiere> fabrication(Integer id_poketra,Integer id_taille,Double quantite,String date) throws Exception {
        List< Production> productions = productionRepository.getAllProducitonBymatiereBypoketa(id_poketra,id_taille);
        List<Sortie_stock_matiere> valiny = new ArrayList<>();

        for (Production p : productions) {
            Reste_stock_matiere r = new Reste_stock_matiere();
            r = r.quantite_entrer(p.getId_matiere(),null);
            double sortie = quantite * p.getQuantite();
            if (r.getSum_entrer() - sortie < 0) {
                throw new Exception("quantite insuffisant");
            }
            else {
                Sortie_stock_matiere s = new Sortie_stock_matiere();
                s.setId_matiere(p.getId_matiere());
                s.setQuantite(sortie);
                s.setDate_sortie(Date.valueOf(date));
                valiny.add(s);
            }

        }

        return valiny;
    }

    public void insert_fabrication(List<Sortie_stock_matiere> list) {
        for (Sortie_stock_matiere s : list) {
            sortieStockMatiereRepository.save(s);
        }
    }

}
