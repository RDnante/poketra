package com.example.poketra.model;

import jakarta.persistence.*;

@Entity(name = "taille")
public class Taille {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id_taille;
    @Column(name = "nom")
    String nom;
    @Column(name = "unite")
    Double unite;

    public Taille() {
    }

    public Integer getId_taille() {
        return id_taille;
    }

    public void setId_taille(Integer id_taille) {
        this.id_taille = id_taille;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Double getUnite() {
        return unite;
    }

    public void setUnite(Double unite) {
        this.unite = unite;
    }
}
