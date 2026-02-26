/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.zentrix.cadire.rest;

import com.zentrix.cadire.db.ConexionMySQL;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.sql.*;
import com.google.gson.Gson;
import com.zentrix.cadire.controller.ControllerUsuario;
import com.zentrix.cadire.model.Usuario;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
 
@Path("usuario")
public class RESTUsuario {
    @Path("login")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@FormParam("nombre") @DefaultValue("") String nombre,
                          @FormParam("contrasenia") @DefaultValue("") String contrasenia)
    {
        String out = null;
        ControllerUsuario cu = new ControllerUsuario();
        Usuario u = null;
        
        try
        {
            u = cu.validate(nombre, contrasenia);
            if(u == null){
                out = """
                      {"error" : "Nombre de usuario o contraseña incorrectos."}
                      """;
            }else{
                out = new Gson().toJson(u);
            }
        }
        catch(Exception e){
            e.printStackTrace();
            out = """
                  {"exception" : "%s"}
                  """;
            out = String.format(out, e.toString());
        }
        return Response.status(Response.Status.OK).entity(out).build();
    }
    
    
}
 