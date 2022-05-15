/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.models;

/**
 *
 * @author cristian-patino
 */
public class ProyectoAsignadoDTO {
    
    private Long idpostulante;
    
    private Long idproyecto;    
    
    private String estado;
    
    private int valor;

    public ProyectoAsignadoDTO() {
    }

    public Long getIdpostulante() {
        return idpostulante;
    }

    public void setIdpostulante(Long idpostulante) {
        this.idpostulante = idpostulante;
    }

    public Long getIdproyecto() {
        return idproyecto;
    }

    public void setIdproyecto(Long idproyecto) {
        this.idproyecto = idproyecto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
    
    
    
}
