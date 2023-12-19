package com.example.poketra.model;

import jakarta.persistence.*;

@Entity(name = "poketra")
public class Poketra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id_poketra;
    @Column(name = "nom")
    String nom;
    @Column(name = "idStyle")
    Integer id_style;
    @Column(name = "idType")
    Integer id_type;
    @Transient
    Style style;
    @Transient
    Type type;

    public Poketra() {
    }

    public Integer getId_poketra() {
        return id_poketra;
    }

    public void setId_poketra(Integer id_poketra) {
        this.id_poketra = id_poketra;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getId_style() {
        return id_style;
    }

    public void setId_style(Integer id_style) {
        this.id_style = id_style;
    }

    public Integer getId_type() {
        return id_type;
    }

    public void setId_type(Integer id_type) {
        this.id_type = id_type;
    }

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
