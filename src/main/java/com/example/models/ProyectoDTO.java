/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.models;

import java.util.Calendar;

/**
 *
 * @author cristian-patino
 */
public class ProyectoDTO {
    
    private String nombre;    
    
    private String descripcion;
    
    private Long responsable;
    
    private Calendar finaldate;
    
    private int valorObjetivo;
    
    private int ValorActual;
    
    private String tipoProyecto;
    
    private Long donante;
    
    private String estado;

    public ProyectoDTO() {
    }

    public int getValorObjetivo() {
        return valorObjetivo;
    }

    public void setValorObjetivo(int valorObjetivo) {
        this.valorObjetivo = valorObjetivo;
    }

    public int getValorActual() {
        return ValorActual;
    }

    public Long getDonante() {
        return donante;
    }

    public void setDonante(Long donante) {
        this.donante = donante;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    
    public void setValorActual(int ValorActual) {
        this.ValorActual = ValorActual;
    }

    public String getTipoProyecto() {
        return tipoProyecto;
    }

    public void setTipoProyecto(String tipoProyecto) {
        this.tipoProyecto = tipoProyecto;
    }

    
    public Calendar getFinaldate() {
        return finaldate;
    }

    public void setFinaldate(Calendar finaldate) {
        this.finaldate = finaldate;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Long getResponsable() {
        return responsable;
    }

    public void setResponsable(Long responsable) {
        this.responsable = responsable;
    }
    
    
}
