package com.example.poketra.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "mpiasa")
public class Mpiasa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id_mpiasa;
    @Column
    Integer id_poste;
    @Column
    String nom;
    @Column
    Date dte_naissance;
    @Column
    Integer annee_exp;
    @Transient
    Status_mpiasa status_mpiasa;
    @Transient
    Date_recrutement date_recrutement;
    @Transient
    Poste poste;
    @Transient
    Double salaire;
}
