package com.example.poketra.repository;

import com.example.poketra.model.Production;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductionRepository extends JpaRepository<Production,Integer> {
    @Query("from production where id_matiere = ?1")
    public List<Production> getAllProductionBymatiere(int id_matiere);

    @Query("from production where id_poketra = ?1 and id_taille = ?2")
    public List<Production> getAllProducitonBymatiereBypoketa(int id_poketra, int id_taille);
}
