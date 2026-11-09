package com.autotech.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
    
   
    private static final String URL = "jdbc:sqlserver://192.168.0.9:1433;databaseName=AutoTechManager;encrypt=true;trustServerCertificate=true;";
    
    
    private static final String USER = "sa"; 
    private static final String PASS = "123456"; 

    public static Connection getConexion() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
