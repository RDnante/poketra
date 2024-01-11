package com.example.poketra.model;

import jakarta.persistence.*;

import java.sql.Date;

@Entity(name = "entrer_stock_matiere")
public class Entrer_stock_matiere {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id_entrer_stock;
    @Column(name = "id_matiere")
    Integer id_matiere;
    @Column(name = "quantite")
    Double quantite;
    @Column(name = "date_entrer")
    Date date_entrer;

    public Entrer_stock_matiere() {
    }

    public Integer getId_entrer_stock() {
        return id_entrer_stock;
    }

    public void setId_entrer_stock(Integer id_entrer_stock) {
        this.id_entrer_stock = id_entrer_stock;
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

    public Date getDate_entrer() {
        return date_entrer;
    }

    public void setDate_entrer(Date date_entrer) {
        this.date_entrer = date_entrer;
    }
}
