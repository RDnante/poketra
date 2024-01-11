package com.example.poketra.model;

import jakarta.persistence.*;

import java.sql.Date;

@Entity(name = "sortie_stock_matiere")
public class Sortie_stock_matiere {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id_sortie_stock_matiere;
    @Column(name = "id_matiere")
    Integer id_matiere;
    @Column(name = "quantite")
    Double quantite;
    @Column(name = "date_sortie")
    Date date_sortie;

    public Sortie_stock_matiere() {
    }

    public Integer getId_sortie_stock_matiere() {
        return id_sortie_stock_matiere;
    }

    public void setId_sortie_stock_matiere(Integer id_sortie_stock_matiere) {
        this.id_sortie_stock_matiere = id_sortie_stock_matiere;
    }

    public Integer getId_matiere() {
        return id_matiere;
    }

    public void setId_matiere(Integer id_matiere) {
        this.id_matiere = id_matiere;
    }

    public Double getQuantite() {
        return quantite;
    }

    public void setQuantite(Double quantite) {
        this.quantite = quantite;
    }

    public Date getDate_sortie() {
        return date_sortie;
    }

    public void setDate_sortie(Date date_sortie) {
        this.date_sortie = date_sortie;
    }
}
