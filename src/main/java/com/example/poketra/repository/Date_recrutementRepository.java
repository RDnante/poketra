package com.example.poketra.repository;

import com.example.poketra.model.Date_recrutement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface Date_recrutementRepository extends JpaRepository<Date_recrutement,Integer> {
    @Query("from date_recrutement where id_mpiasa = ?1")
    public Date_recrutement getDate_recrument(int id_mpiasa);
}
