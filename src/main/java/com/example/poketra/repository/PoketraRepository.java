package com.example.poketra.repository;

import com.example.poketra.model.Poketra;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PoketraRepository extends JpaRepository<Poketra,Integer> {
}
