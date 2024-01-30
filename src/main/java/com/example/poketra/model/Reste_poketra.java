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
@Entity(name = "reste_poketra")
public class Reste_poketra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id_poketra;

    @Column
    Integer reste;
}
