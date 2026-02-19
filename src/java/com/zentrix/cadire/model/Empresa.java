/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.zentrix.cadire.model;

/**
 *
 * @author garci
 */

import java.sql.Timestamp;

public class Empresa {

    private int id;
    private String nombreEmpresa;
    private String direccion;
    private String colonia;
    private String ciudad;
    private String estado;
    private String telefono;
    private String horarioAtencion;
    private double latitud;
    private double longitud;
    private String sitioWeb;
    private boolean verificada;
    private Timestamp fechaRegistro;

    public Empresa() {
    }

    public Empresa(int id, String nombreEmpresa, String direccion, String colonia,
                   String ciudad, String estado, String telefono,
                   String horarioAtencion, double latitud, double longitud,
                   String sitioWeb, boolean verificada, Timestamp fechaRegistro) {
        this.id = id;
        this.nombreEmpresa = nombreEmpresa;
        this.direccion = direccion;
        this.colonia = colonia;
        this.ciudad = ciudad;
        this.estado = estado;
        this.telefono = telefono;
        this.horarioAtencion = horarioAtencion;
        this.latitud = latitud;
        this.longitud = longitud;
        this.sitioWeb = sitioWeb;
        this.verificada = verificada;
        this.fechaRegistro = fechaRegistro;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombreEmpresa() { return nombreEmpresa; }
    public void setNombreEmpresa(String nombreEmpresa) { this.nombreEmpresa = nombreEmpresa; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getColonia() { return colonia; }
    public void setColonia(String colonia) { this.colonia = colonia; }

    public String getCiudad() { return ciudad; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getHorarioAtencion() { return horarioAtencion; }
    public void setHorarioAtencion(String horarioAtencion) { this.horarioAtencion = horarioAtencion; }

    public double getLatitud() { return latitud; }
    public void setLatitud(double latitud) { this.latitud = latitud; }

    public double getLongitud() { return longitud; }
    public void setLongitud(double longitud) { this.longitud = longitud; }

    public String getSitioWeb() { return sitioWeb; }
    public void setSitioWeb(String sitioWeb) { this.sitioWeb = sitioWeb; }

    public boolean isVerificada() { return verificada; }
    public void setVerificada(boolean verificada) { this.verificada = verificada; }

    public Timestamp getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(Timestamp fechaRegistro) { this.fechaRegistro = fechaRegistro; }
}

