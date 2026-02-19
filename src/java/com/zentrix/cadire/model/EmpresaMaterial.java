/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.zentrix.cadire.model;

/**
 *
 * @author Edgar Gómez
 */

import java.math.BigDecimal;
import java.sql.Date;

public class EmpresaMaterial {

    private int id;
    private int empresaId;
    private int materialId;
    private BigDecimal precio;
    private BigDecimal precioKg;
    private BigDecimal precioTonelada;
    private String condicionCompra;
    private Date ultimaActualizacion;

    public EmpresaMaterial() {
    }

    public EmpresaMaterial(int id, int empresaId, int materialId,
                           BigDecimal precio, BigDecimal precioKg,
                           BigDecimal precioTonelada,
                           String condicionCompra, Date ultimaActualizacion) {
        this.id = id;
        this.empresaId = empresaId;
        this.materialId = materialId;
        this.precio = precio;
        this.precioKg = precioKg;
        this.precioTonelada = precioTonelada;
        this.condicionCompra = condicionCompra;
        this.ultimaActualizacion = ultimaActualizacion;
    }

    // Getters y Setters

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getEmpresaId() { return empresaId; }
    public void setEmpresaId(int empresaId) { this.empresaId = empresaId; }

    public int getMaterialId() { return materialId; }
    public void setMaterialId(int materialId) { this.materialId = materialId; }

    public BigDecimal getPrecio() { return precio; }
    public void setPrecio(BigDecimal precio) { this.precio = precio; }

    public BigDecimal getPrecioKg() { return precioKg; }
    public void setPrecioKg(BigDecimal precioKg) { this.precioKg = precioKg; }

    public BigDecimal getPrecioTonelada() { return precioTonelada; }
    public void setPrecioTonelada(BigDecimal precioTonelada) { this.precioTonelada = precioTonelada; }

    public String getCondicionCompra() { return condicionCompra; }
    public void setCondicionCompra(String condicionCompra) { this.condicionCompra = condicionCompra; }

    public Date getUltimaActualizacion() { return ultimaActualizacion; }
    public void setUltimaActualizacion(Date ultimaActualizacion) { this.ultimaActualizacion = ultimaActualizacion; }
}

