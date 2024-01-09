package com.example.poketra.repository;

import com.example.poketra.model.Production_prix_total;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Production_prix_totalRepository extends JpaRepository<Production_prix_total,Integer> {
    @Query("select id_poketra from production_prix_total where total > ?1 and total < ?2")
    public List<Integer> getAllidPoketreByPrix(Double prix1, Double prix2);
}
