package com.example.poketra.model;

import jakarta.persistence.*;

@Entity(name = "production")
public class Production {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id_production;
    @Column(name = "id_poketra")
    Integer id_poketra;
    @Column(name = "id_matiere")
    Integer id_matiere;
    @Column(name = "id_taille")
    Integer id_taille;
    @Column(name = "quantite")
    Double quantite;
    @Transient
    Poketra poketra;
    @Transient
    Taille taille;

    public Production() {
    }

    public Integer getId_production() {
        return id_production;
    }

    public void setId_production(Integer id_production) {
        this.id_production = id_production;
    }

    public Integer getId_poketra() {
        return id_poketra;
    }

    public Poketra getPoketra() {
        return poketra;
    }

    public void setPoketra(Poketra poketra) {
        this.poketra = poketra;
    }

    public Taille getTaille() {
        return taille;
    }

    public void setTaille(Taille taille) {
        this.taille = taille;
    }

    public void setId_poketra(Integer id_poketra) {
        this.id_poketra = id_poketra;
    }

    public Integer getId_matiere() {
        return id_matiere;
    }

    public void setId_matiere(Integer id_matiere) {
        this.id_matiere = id_matiere;
    }

    public Integer getId_taille() {
        return id_taille;
    }

    public void setId_taille(Integer id_taille) {
        this.id_taille = id_taille;
    }

    public Double getQuantite() {
        return quantite;
    }

    public void setQuantite(Double quantite) {
        this.quantite = quantite;
    }
}
