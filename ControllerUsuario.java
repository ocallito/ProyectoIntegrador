/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.zantrax.cadire.controller;

import com.zantrax.cadire.db.ConexionMySQL;
import com.zantrax.cadire.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ControllerUsuario {

    public Usuario validate(String nombre, String contrasenia) throws Exception {

        String sql = "SELECT * FROM usuario WHERE nombre=? AND contrasenia=?";

        ConexionMySQL connMySQL = new ConexionMySQL();
        Connection conn = connMySQL.open();

        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = null;
        Usuario u = null;

        pstmt.setString(1, nombre);
        pstmt.setString(2, contrasenia);

        rs = pstmt.executeQuery();

        if (rs.next()) {
            u = fill(rs);
        }

        rs.close();
        pstmt.close();
        conn.close();

        return u;
    }

    private Usuario fill(ResultSet rs) throws Exception {

        Usuario u = new Usuario();

        u.setId(rs.getInt("id"));
        u.setNombre(rs.getString("nombre"));
        u.setContrasenia(rs.getString("contrasenia"));
        u.setRol(rs.getString("rol"));

        return u;
    }
}