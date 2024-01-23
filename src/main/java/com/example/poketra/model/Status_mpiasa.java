package com.example.poketra.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "status_mpiasa")
public class Status_mpiasa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id_status_mpiasa;
    @Column
    String nom;
    @Column
    Integer min_diff;
    @Column
    Integer max_diff;
    @Column
    Double valeur;
}
