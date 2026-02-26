/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.zantrax.cadire.db;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author garci
 */
public class ConexionMySQL {
    Connection conn;
    
    public Connection open() throws Exception
    {
        String url = "jdbc:mysql://127.0.0.1:3306/reciclaje_leon";
        String usuario = "root";
        String password = "root"; 
        
        
        Class.forName("com.mysql.cj.jdbc.Driver");
        
        conn = DriverManager.getConnection(url, usuario, password);
        
        return conn;
    }
    
    public void close() throws Exception
    {
        if (conn != null)
            conn.close();
    }
}

