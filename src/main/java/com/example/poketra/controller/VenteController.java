package com.example.poketra.controller;

import com.example.poketra.Service.VenteService;
import com.example.poketra.model.Client;
import com.example.poketra.model.Poketra;
import com.example.poketra.model.Vente;
import com.example.poketra.model.VenteParGenre;
import com.example.poketra.repository.ClientRepository;
import com.example.poketra.repository.PoketraRepository;
import com.example.poketra.repository.VenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/vente")
public class VenteController {
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    PoketraRepository poketraRepository;
    @Autowired
    VenteRepository venteRepository;
    @Autowired
    VenteService venteService;
    @GetMapping("/aff")
    public String aff(Model model) {
        return "client_insert";
    }

    @PostMapping("/insertclient")
    public String isnertclient(Model model, @RequestParam("nom")String nom,@RequestParam("genre") int genre) {
        Client client = new Client();
        client.setNom(nom);
        client.setGenre(genre);

        clientRepository.save(client);

        return "redirect:/vente/aff";
    }

    @GetMapping("/affvente")
    public String affvente(Model model) {
        List<Client> clientList = clientRepository.findAll();
        List<Poketra> poketraList = poketraRepository.findAll();

        model.addAttribute("clientlist",clientList);
        model.addAttribute("poketralist",poketraList);

        return "vente_insert";
    }

    @PostMapping("/insertvente")
    public String insertvente(Model model,@RequestParam("client") int id_client,@RequestParam("poketra") int id_poketra,@RequestParam("date") String date,@RequestParam("nombre")int nombre) {


        try {
            Vente v = new Vente();
            v.setId_client(id_client);
            v.setId_poketra(id_poketra);
            v.setDate_vente(Date.valueOf(date));
            v.setNombre(nombre);
            venteRepository.save(v);

            return "redirect:/vente/affvente";

        }
        catch (Exception e) {
            model.addAttribute("message",e.getMessage());
            return "erreur";
        }


    }

    @GetMapping("/afffiltre")
    public String afffiltre(Model model) {

        List<Poketra> poketraList = poketraRepository.findAll();
        model.addAttribute("poketralist",poketraList);

        return "vente_filtre";
    }

    @PostMapping("/insfiltre")
    public String insfiltre(Model model,@RequestParam("poketra") int poketra) {
        try {
            List<VenteParGenre> listgenre = venteService.getTotalGenre(null,poketra);

            model.addAttribute("listgenre",listgenre);
            List<String> genre = Arrays.asList("Femme","Homme");
            int sumfemme = 0;
            int sumhomme = 0;
            List<Integer> nombre = new ArrayList<>();
            for (VenteParGenre v : listgenre) {
                if (v.getGenre() == 0) {
                    sumfemme = v.getSum();
                }
                else if (v.getGenre() == 1) {
                    sumhomme = v.getSum();
                }
//                nombre.add(v.getSum());
            }

            nombre.add(sumfemme);
            nombre.add(sumhomme);

            model.addAttribute("genre",genre);
            model.addAttribute("nombre",nombre);
            model.addAttribute("sumfemme",sumfemme);
            model.addAttribute("sumhomme",sumhomme);

        }catch (Exception e) {

        }


        return "vente_stats";
    }
}
