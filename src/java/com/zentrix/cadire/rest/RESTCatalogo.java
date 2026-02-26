/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.zentrix.cadire.rest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.zentrix.cadire.controller.Catalogo;
import com.zentrix.cadire.db.ConexionMySQL;
import com.zentrix.cadire.model.Empresa;
import java.util.List;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("catalogo")
public class RESTCatalogo {
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {

        Catalogo c = new Catalogo();
        List<Empresa> lista = c.getAll();

        return Response.ok(lista).build();
    }
    @GET
    @Path("material/{nombre}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarPorMaterial(@PathParam("nombre") String nombre) {

        Catalogo c = new Catalogo();
        List<Empresa> lista = c.buscarPorMaterial(nombre);

        return Response.ok(lista).build();
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Empresa insertar(Empresa e) {

    try {
        ConexionMySQL connMySQL = new ConexionMySQL();
        Connection conn = connMySQL.open();

        String query = "INSERT INTO empresa(nombre, direccion, telefono, material, precio_kg, ciudad, lat, lng) VALUES(?,?,?,?,?,?,?,?)";

        PreparedStatement ps = conn.prepareStatement(query);

        ps.setString(1, e.getNombre());
        ps.setString(2, e.getDireccion());
        ps.setString(3, e.getTelefono());
        ps.setString(4, e.getMaterial());
        ps.setDouble(5, e.getPrecioKg());
        ps.setString(6, e.getCiudad());
        ps.setDouble(7, e.getLat());
        ps.setDouble(8, e.getLng());

        ps.executeUpdate();
        connMySQL.close();

    } catch (Exception ex) {
        ex.printStackTrace();
    }

    return e;
}
    @DELETE
    @Path("{id}")
    public void eliminar(@PathParam("id") int id) {

        try {
            ConexionMySQL connMySQL = new ConexionMySQL();
            Connection conn = connMySQL.open();

            String query = "DELETE FROM empresa WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);

            ps.executeUpdate();
            connMySQL.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
}
}
