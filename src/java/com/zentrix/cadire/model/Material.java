/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.zentrix.cadire.model;

/**
 *
 * @author Edgar Gómez
 */

public class Material {

    private int id;
    private String nombreMaterial;
    private String categoria;
    private String descripcion;
    private String unidadMedida;
    private String icono;

    public Material() {
    }

    public Material(int id, String nombreMaterial, String categoria,
                    String descripcion, String unidadMedida, String icono) {
        this.id = id;
        this.nombreMaterial = nombreMaterial;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.unidadMedida = unidadMedida;
        this.icono = icono;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombreMaterial() { return nombreMaterial; }
    public void setNombreMaterial(String nombreMaterial) { this.nombreMaterial = nombreMaterial; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getUnidadMedida() { return unidadMedida; }
    public void setUnidadMedida(String unidadMedida) { this.unidadMedida = unidadMedida; }

    public String getIcono() { return icono; }
    public void setIcono(String icono) { this.icono = icono; }
}

