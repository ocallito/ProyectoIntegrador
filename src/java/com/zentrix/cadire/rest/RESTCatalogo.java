/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.zentrix.cadire.rest;

/**
 *
 * @author garci
 */
import com.zentrix.cadire.controller.Catalogo;
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
}