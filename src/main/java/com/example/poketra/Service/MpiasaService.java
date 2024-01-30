package com.example.poketra.Service;

import com.example.poketra.model.Mpiasa;
import com.example.poketra.model.Status_mpiasa;
import com.example.poketra.repository.Date_recrutementRepository;
import com.example.poketra.repository.MpiasaRepository;
import com.example.poketra.repository.PosteRepository;
import com.example.poketra.repository.Status_mpiasaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class MpiasaService {
    @Autowired
    MpiasaRepository mpiasaRepository;
    @Autowired
    Status_mpiasaRepository statusMpiasaRepository;
    @Autowired
    Date_recrutementRepository dateRecrutementRepository;
    @Autowired
    PosteRepository posteRepository;
    public List<Mpiasa> traitement_tauxHoraire(String date) {
        List<Mpiasa> valiny = new ArrayList<>();
        valiny =  mpiasaRepository.findAll();

        for (Mpiasa m : valiny) {
            this.traitement_status(m,date);
        }

        return valiny;
    }

    public void traitement_status(Mpiasa mpiasa,String date) {
        mpiasa.setDate_recrutement(dateRecrutementRepository.getDate_recrument(mpiasa.getId_mpiasa()));
        mpiasa.setPoste(posteRepository.findById(mpiasa.getId_poste()).get());
        int exp = mpiasa.getAnnee_exp();
        LocalDate anneezao = LocalDate.parse(date);
        LocalDate recru = LocalDate.parse(mpiasa.getRecrutement().toString());
        int age = Math.abs(recru.getYear() - anneezao.getYear());
        int val = exp + age;
        System.out.println("val"+val);

        Status_mpiasa status_mpiasa = statusMpiasaRepository.get_status_mpiasa(val);
        mpiasa.setStatus_mpiasa(status_mpiasa);

        mpiasa.setSalaire(mpiasa.getPoste().getSalaire() * mpiasa.getStatus_mpiasa().getValeur());
    }
}
