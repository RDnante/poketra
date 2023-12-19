package com.example.poketra.repository;

import com.example.poketra.model.Taille;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TailleRepository extends JpaRepository<Taille,Integer> {
}
