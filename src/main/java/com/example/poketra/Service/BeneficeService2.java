package com.example.poketra.Service;

import com.example.poketra.connect.ConnectJava;
import com.example.poketra.repository.TailleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

@Service
public class BeneficeService2 {
    @Autowired
    TailleRepository tailleRepository;
    public int getefamiasa(Connection connection,int id_taille) throws Exception {
        int valiny;
        boolean verif = false;
        try {
            if (connection == null) {
                verif = true;
                connection = new ConnectJava().getConnection();
            }
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select count(id_taille) as count from main_oeuvre_taille where  id_taille ="+id_taille);
            valiny = resultSet.getInt("count");
        }catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        finally {
            if (verif == true) {
                connection.close();
            }
        }

        return valiny;
    }

    public int getMaxMiasa(Connection connection,int id_taille) throws Exception {
        int valiny;
        boolean verif = false;
        try {
            if (connection == null) {
                verif = true;
                connection = new ConnectJava().getConnection();
            }
            if (id_taille > 1) {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select ((t.id_taille - 1) * (select valeur from min_taille) * (select valeur from max_taille)) as max from taille as t where t.id_taille = "+id_taille);
                valiny = resultSet.getInt("max");
            }
            else {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select valeur from min_taille");
                valiny = resultSet.getInt("valeur");
            }

        }catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        finally {
            if (verif == true) {
                connection.close();
            }
        }

        return valiny;
    }

    public void inserer_ouvrier(Connection connection,int id_ouvrier,int id_taille) throws Exception {
        boolean verif = false;
        try {
            if (connection == null) {
                verif = true;
                connection = new ConnectJava().getConnection();
            }
            int efamiasa = this.getefamiasa(connection,id_taille);
            int maxmiada = this.getMaxMiasa(connection,id_taille);
            if (efamiasa >= maxmiada) {
                throw new Exception("valeur maximale atteinte pour "+tailleRepository.findById(id_taille).get().getNom());
            }
            Statement statement = connection.createStatement();
            statement.executeUpdate("insert into main_oeuvre_taille values ("+id_taille+","+id_ouvrier+")");

        }catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        finally {
            if (verif == true) {
                connection.close();
            }
        }
    }
}
