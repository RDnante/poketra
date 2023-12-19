package com.example.poketra.model;

import jakarta.persistence.*;

@Entity(name = "style")
public class Style {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id_style;
    @Column(name = "nom")
    String nom;

    public Style() {
    }

    public Integer getId_style() {
        return id_style;
    }

    public void setId_style(Integer id_style) {
        this.id_style = id_style;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
