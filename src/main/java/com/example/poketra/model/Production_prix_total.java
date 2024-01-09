package com.example.poketra.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "production_prix_total")
public class Production_prix_total {
    @Id
    Integer id_poketra;
    @Column(name = "total")
    Double total;

    public Production_prix_total() {
    }

    public Integer getId_poketra() {
        return id_poketra;
    }

    public void setId_poketra(Integer id_poketra) {
        this.id_poketra = id_poketra;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
