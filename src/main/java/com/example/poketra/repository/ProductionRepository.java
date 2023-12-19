package com.example.poketra.repository;

import com.example.poketra.model.Production;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductionRepository extends JpaRepository<Production,Integer> {
    
}
