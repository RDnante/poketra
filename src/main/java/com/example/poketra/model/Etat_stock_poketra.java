package com.example.poketra.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "etat_stock_poketra")
public class Etat_stock_poketra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id_etat_stock_poketra;

    @Column
    Integer id_poketra;
    @Column
    Integer entrer;
    @Column
    Integer sortie;
    @Column
    Date date;

    public Integer getEntrer() {
        return entrer;
    }

    public void setEntrer(Integer entrer) throws Exception {
        if (entrer < 0) throw new Exception("valeur negatif ou null invalide");
        this.entrer = entrer;
    }

    public Integer getSortie() {
        return sortie;
    }

    public void setSortie(Integer sortie) throws Exception {
        if (sortie < 0) throw new Exception("valeur negatif ou null invalide");
        this.sortie = sortie;
    }
}
