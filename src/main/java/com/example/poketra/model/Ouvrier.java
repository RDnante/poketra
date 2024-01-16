package com.example.poketra.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Ouvrier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id_ouvrier;

    @Column
    String nom;

    @Column
    Double prix;
}
