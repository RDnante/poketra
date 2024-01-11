package com.example.poketra;

import com.example.poketra.model.Reste_stock_matiere;

public class Testemain {
    public static void main(String[] args) {
        Reste_stock_matiere r = new Reste_stock_matiere();
        try {
            System.out.println(r.getAllReste(null).get(0).getId_matiere());
        }catch (Exception e) {

        }
    }
}
