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
@Entity(name = "date_recrutement")
public class Date_recrutement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id_date_recrutement;
    @Column
    Integer id_mpiasa;
    @Column
    Date date_recrutement;
}
