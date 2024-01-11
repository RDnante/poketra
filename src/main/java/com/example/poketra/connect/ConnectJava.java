package com.example.poketra.connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectJava {

    public Connection getConnection() throws Exception {
        Connection con = null;

        String dbURL3 = "jdbc:postgresql://localhost:5432/poketra";
        Properties parameters = new Properties();
        parameters.put("user", "postgres");
        parameters.put("password", "28oct2003");

        con = DriverManager.getConnection(dbURL3,parameters);

        return con;
    }
}
