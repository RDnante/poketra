package com.example.poketra.model;

import com.example.poketra.connect.ConnectJava;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Reste_stock_matiere {
    Integer id_matiere;
    Double sum_entrer;
    Double sum_sortie;
    Double reste;

    public Reste_stock_matiere() {
    }

    public Integer getId_matiere() {
        return id_matiere;
    }

    public void setId_matiere(Integer id_matiere) {
        this.id_matiere = id_matiere;
    }

    public Double getSum_entrer() {
        return sum_entrer;
    }

    public void setSum_entrer(Double sum_entrer) {
        this.sum_entrer = sum_entrer;
    }

    public Double getSum_sortie() {
        return sum_sortie;
    }

    public void setSum_sortie(Double sum_sortie) {
        this.sum_sortie = sum_sortie;
    }

    public Double getReste() {
        return reste;
    }

    public void setReste(Double reste) {
        this.reste = reste;
    }

    public List<Reste_stock_matiere> getAllReste(Connection connection) throws Exception {
        List<Reste_stock_matiere> valiny = new ArrayList<Reste_stock_matiere>();
        boolean verif = false;
        try {
            if (connection == null) {
                verif = true;
                connection = new ConnectJava().getConnection();
            }
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from reste_stock_matiere");
            while (resultSet.next()) {
                Reste_stock_matiere r = new Reste_stock_matiere();
                r.setId_matiere(resultSet.getInt("id_matiere"));
                r.setSum_entrer(resultSet.getDouble("sum_entrer"));
                r.setSum_sortie(resultSet.getDouble("sum_sortie"));
                r.setReste(resultSet.getDouble("reste"));

                valiny.add(r);
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (verif == true) {
                connection.close();
            }
        }

        return valiny;
    }

    public Reste_stock_matiere quantite_entrer(int id_matiere,Connection connection) throws Exception {
        Reste_stock_matiere valiny = new Reste_stock_matiere();
        boolean verif = false;
        try {
            if (connection == null) {
                verif = true;
                connection = new ConnectJava().getConnection();
            }
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from reste_stock_matiere where id_matiere = "+id_matiere);
            while (resultSet.next()) {
                valiny.setId_matiere(resultSet.getInt("id_matiere"));
                valiny.setSum_entrer(resultSet.getDouble("sum_entrer"));
                valiny.setSum_sortie(resultSet.getDouble("sum_sortie"));
                valiny.setReste(resultSet.getDouble("reste"));
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (verif == true) {
                connection.close();
            }
        }

        return valiny;
    }
}
