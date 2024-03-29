package com.example.practicasbexamenmarzo.clase;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "informacioncliente")

public class InformacionCliente implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombrecliente")
    private String nombreCliente;

    private String sexo;

    private Double peso;

    private Long edad;

    private Double talla;
    @Column(name = "tipoactividad")
    private String tipoActividad;

    private String observaciones;

    private Double ger;

    private double geto;

    public InformacionCliente(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Long getEdad() {
        return edad;
    }

    public void setEdad(Long edad) {
        this.edad = edad;
    }

    public Double getTalla() {
        return talla;
    }

    public void setTalla(Double talla) {
        this.talla = talla;
    }

    public String getTipoActividad() {
        return tipoActividad;
    }

    public void setTipoActividad(String tipoActividad) {
        this.tipoActividad = tipoActividad;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Double getGer() {
        return ger;
    }

    public void setGer(Double ger) {
        this.ger = ger;
    }

    public double getGeto() {
        return geto;
    }

    public void setGeto(double geto) {
        this.geto = geto;
    }
}
