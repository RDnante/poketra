package com.example.poketra.Service;

import com.example.poketra.connect.ConnectJava;
import com.example.poketra.model.Reste_stock_matiere;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BeneficeService {

    public void update_max_style(Connection connection, double max) throws Exception {
        boolean verif = false;
        try {
            if (connection == null) {
                verif = true;
                connection = new ConnectJava().getConnection();
            }
            Statement statement = connection.createStatement();
            statement.executeUpdate("update max_style set valeur = "+max);

        }catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (verif == true) {
                connection.close();
            }
        }
    }
    public void update_max_taille(Connection connection, double max) throws Exception {
        boolean verif = false;
        try {
            if (connection == null) {
                verif = true;
                connection = new ConnectJava().getConnection();
            }
            Statement statement = connection.createStatement();
            statement.executeUpdate("update max_taille set valeur = "+max);

        }catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (verif == true) {
                connection.close();
            }
        }
    }
    public void update_min_taille(Connection connection, double min) throws Exception {
        boolean verif = false;
        try {
            if (connection == null) {
                verif = true;
                connection = new ConnectJava().getConnection();
            }
            Statement statement = connection.createStatement();
            statement.executeUpdate("update min_taille set valeur = "+min);

        }catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (verif == true) {
                connection.close();
            }
        }
    }
    public void update_duree_min_style(Connection connection, double max) throws Exception {
        boolean verif = false;
        try {
            if (connection == null) {
                verif = true;
                connection = new ConnectJava().getConnection();
            }
            Statement statement = connection.createStatement();
            statement.executeUpdate("update duree_min_style set valeur = "+max);

        }catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (verif == true) {
                connection.close();
            }
        }
    }

}
