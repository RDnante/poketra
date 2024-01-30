package com.example.poketra.Service;

import com.example.poketra.connect.ConnectJava;
import com.example.poketra.model.Reste_stock_matiere;
import com.example.poketra.model.VenteParGenre;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Service
public class VenteService {

    public List<VenteParGenre> getTotalGenre(Connection connection, int id_poketra) throws Exception {
        List<VenteParGenre> valiny = new ArrayList<VenteParGenre>();
        boolean verif = false;
        try {
            if (connection == null) {
                verif = true;
                connection = new ConnectJava().getConnection();
            }
            Statement statement = connection.createStatement();
            ResultSet resultSet = null;
            if (id_poketra != 999) {
                resultSet = statement.executeQuery("select * from vente_par_genre where  id_poketra ="+id_poketra);
                System.out.println("select * from vente_par_genre where  id_poketra ="+id_poketra);
            } else {
                resultSet = statement.executeQuery("select sum(nombre),genre from vente_client group by genre");
                System.out.println("select sum(nombre),genre from vente_client group by genre");

            }
            while (resultSet.next()) {
                VenteParGenre v = new VenteParGenre();
                v.setSum(resultSet.getInt("sum"));
                v.setGenre(resultSet.getInt("genre"));
                if (id_poketra != 999) {
                    v.setId_poketra(resultSet.getInt("id_poketra"));
                }
                valiny.add(v);
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
