package com.example.poketra.Service;

import com.example.poketra.model.Etat_stock_poketra;
import com.example.poketra.repository.Etat_stock_poketraRepository;
import com.example.poketra.repository.Reste_poketraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class Stock_poketraService {

    @Autowired
    Etat_stock_poketraRepository etatStockPoketraRepository;
    @Autowired
    Reste_poketraRepository restePoketraRepository;

    public void vente(int id_poketra,int vente, String date) throws Exception {
        int reste = restePoketraRepository.findById(id_poketra).get().getReste();
        int diff = reste - vente;
        if (diff < 0) throw  new Exception("quantite en stock insuffisant");

        Etat_stock_poketra e = new Etat_stock_poketra();
        e.setId_poketra(id_poketra);
        e.setEntrer(0);
        e.setSortie(vente);
        e.setDate(Date.valueOf(date));

        etatStockPoketraRepository.save(e);

    }
}
