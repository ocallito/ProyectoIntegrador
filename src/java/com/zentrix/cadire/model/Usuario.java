/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.zentrix.cadire.model;

/**
 *
 * @author Edgar Gómez
 */

import java.sql.Timestamp;

public class Usuario {

    private int id;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String email;
    private String passwordHash;
    private String tipoUsuario;
    private Integer empresaId;
    private String telefono;
    private Timestamp fechaRegistro;
    private Timestamp ultimoAcceso;
    private boolean activo;

    public Usuario() {
    }

    public Usuario(int id, String nombre, String apellidoPaterno,
                   String apellidoMaterno, String email,
                   String passwordHash, String tipoUsuario,
                   Integer empresaId, String telefono,
                   Timestamp fechaRegistro, Timestamp ultimoAcceso,
                   boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.email = email;
        this.passwordHash = passwordHash;
        this.tipoUsuario = tipoUsuario;
        this.empresaId = empresaId;
        this.telefono = telefono;
        this.fechaRegistro = fechaRegistro;
        this.ultimoAcceso = ultimoAcceso;
        this.activo = activo;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellidoPaterno() { return apellidoPaterno; }
    public void setApellidoPaterno(String apellidoPaterno) { this.apellidoPaterno = apellidoPaterno; }

    public String getApellidoMaterno() { return apellidoMaterno; }
    public void setApellidoMaterno(String apellidoMaterno) { this.apellidoMaterno = apellidoMaterno; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }

    public String getTipoUsuario() { return tipoUsuario; }
    public void setTipoUsuario(String tipoUsuario) { this.tipoUsuario = tipoUsuario; }

    public Integer getEmpresaId() { return empresaId; }
    public void setEmpresaId(Integer empresaId) { this.empresaId = empresaId; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public Timestamp getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(Timestamp fechaRegistro) { this.fechaRegistro = fechaRegistro; }

    public Timestamp getUltimoAcceso() { return ultimoAcceso; }
    public void setUltimoAcceso(Timestamp ultimoAcceso) { this.ultimoAcceso = ultimoAcceso; }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }
}

