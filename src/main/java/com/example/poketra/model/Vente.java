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
@Entity(name = "vente")
public class Vente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id_vente;
    @Column
    Integer id_poketra;
    @Column
    Integer id_client;
    @Column
    Date date_vente;
    @Column
    Integer nombre;

    public void setNombre(Integer nombre) throws Exception{
        if (nombre <= 0) {
            throw new Exception("nombre invalide");
        }
        this.nombre = nombre;
    }
}
