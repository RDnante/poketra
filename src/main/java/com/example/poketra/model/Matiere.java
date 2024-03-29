package com.example.poketra.model;

import com.example.poketra.repository.MatiereRepository;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

@Entity(name = "matiere")
public class Matiere {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id_matiere;
    @Column(name = "nom")
    String nom;
    @Column(name = "luxure")
    Integer luxure;
    @Column(name = "prix")
    Double prix;

    public Matiere() {
    }

    public Integer getId_matiere() {
        return id_matiere;
    }

    public void setId_matiere(Integer id_matiere) {
        this.id_matiere = id_matiere;
    }

    public Integer getLuxure() {
        return luxure;
    }

    public void setLuxure(Integer luxure) {
        this.luxure = luxure;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) throws Exception {
        if (prix < 0) {
            throw new Exception("pas de valeur negative");
        }
        this.prix = prix;
    }
}
