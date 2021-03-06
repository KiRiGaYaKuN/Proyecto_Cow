/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.models;

import com.sun.istack.NotNull;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;
import static javax.persistence.CascadeType.ALL;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
public class Proyecto implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @NotNull
    @Column(name = "iniciodate", updatable = false)
    @Temporal(TemporalType.DATE)
    private Calendar iniciodate;
       
    @NotNull
    @Column(name = "finaldate", updatable = false)
    @Temporal(TemporalType.DATE)
    
    private Calendar finaldate;
    
    private String nombre;    
    
    private String descripcion;
    
    private Long responsable;
    
    private int valorObjetivo;
    
    private int ValorActual;
    
    private String tipoProyecto;
    
    private Long donante;
    
    private String estado;

    public Proyecto() {
    }

    @PrePersist
    private void creationTimestamp() {
    this.iniciodate = Calendar.getInstance();
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

    public void setValorActual(int ValorActual) {
        this.ValorActual = ValorActual;
    }

    public String getTipoProyecto() {
        return tipoProyecto;
    }

    public void setTipoProyecto(String tipoProyecto) {
        this.tipoProyecto = tipoProyecto;
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
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
