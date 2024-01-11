package com.example.poketra.model;

import com.example.poketra.connect.ConnectJava;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class Production_prix_total {

    Integer id_poketra;

    Double id_taille;

    Double total;

    public Production_prix_total() {
    }

    public Integer getId_poketra() {
        return id_poketra;
    }

    public void setId_poketra(Integer id_poketra) {
        this.id_poketra = id_poketra;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getId_taille() {
        return id_taille;
    }

    public void setId_taille(Double id_taille) {
        this.id_taille = id_taille;
    }

    public List<Production_prix_total> getall(double prix1, double prix2) throws Exception {
        List<Production_prix_total> valiny = new ArrayList<>();
        try {


            Connection connection = new ConnectJava().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from production_prix_total where total >= "+prix1+" and total <= "+prix2);
            while (resultSet.next()) {
                Production_prix_total p = new Production_prix_total();
                p.setId_poketra(resultSet.getInt("id_poketra"));
                p.setId_taille(resultSet.getDouble("id_taille"));
                valiny.add(p);
            }

            return valiny;
        }catch (Exception e) {

        }

        return valiny;
    }

    public Double total_Prix(Integer id_poketra,int id_taille) throws Exception {
        Double valiny = 0.0;

        try {
            Connection connection = new ConnectJava().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select total from production_prix_total where id_poketra = "+id_poketra+" and id_taille = "+id_taille);
            while (resultSet.next()) {
                valiny = resultSet.getDouble("total");
            }

            return valiny;
        }catch (Exception e) {

        }

        return valiny;
    }
}
