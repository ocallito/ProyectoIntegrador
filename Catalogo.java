/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.zantrax.cadire.controller;
import com.zantrax.cadire.db.ConexionMySQL;
import com.zantrax.cadire.model.Empresa;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author garci
 */
public class Catalogo {

    public List<Empresa> getAll() {

        List<Empresa> lista = new ArrayList<>();

        try {
            ConexionMySQL connMySQL = new ConexionMySQL();
            Connection conn = connMySQL.open();

            String sql = "SELECT * FROM empresa ORDER BY precio_kg DESC";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Empresa e = new Empresa();

                e.setId(rs.getInt("id"));
                e.setNombre(rs.getString("nombre"));
                e.setDireccion(rs.getString("direccion"));
                e.setTelefono(rs.getString("telefono"));
                e.setMaterial(rs.getString("material"));
                e.setPrecioKg(rs.getDouble("precio_kg"));
                e.setCiudad(rs.getString("ciudad"));
                e.setLat(rs.getDouble("lat"));
                e.setLng(rs.getDouble("lng"));

                lista.add(e);
            }

            rs.close();
            pstmt.close();
            connMySQL.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return lista;
    }
    public List<Empresa> buscarPorMaterial(String material) {

        List<Empresa> lista = new ArrayList<>();

        try {
            ConexionMySQL connMySQL = new ConexionMySQL();
            Connection conn = connMySQL.open();

            String sql = "SELECT * FROM empresa " +
                         "WHERE LOWER(material) = LOWER(?) " +
                         "ORDER BY precio_kg DESC";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, material);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Empresa e = new Empresa();

                e.setId(rs.getInt("id"));
                e.setNombre(rs.getString("nombre"));
                e.setDireccion(rs.getString("direccion"));
                e.setTelefono(rs.getString("telefono"));
                e.setMaterial(rs.getString("material"));
                e.setPrecioKg(rs.getDouble("precio_kg"));
                e.setCiudad(rs.getString("ciudad"));
                e.setLat(rs.getDouble("lat"));
                e.setLng(rs.getDouble("lng"));

                lista.add(e);
            }

            rs.close();
            pstmt.close();
            connMySQL.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return lista;
    }
}

