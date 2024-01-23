package com.example.poketra.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "poste")
public class Poste {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id_poste;
    @Column
    String nom;
    @Column
    Double salaire;
}
