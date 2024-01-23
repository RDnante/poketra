package com.example.poketra.repository;

import com.example.poketra.model.Status_mpiasa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface Status_mpiasaRepository extends JpaRepository<Status_mpiasa,Integer> {
    @Query("from status_mpiasa where min_diff <= ?1 and max_diff >= ?1")
    public Status_mpiasa get_status_mpiasa(int age);
}
