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
}
