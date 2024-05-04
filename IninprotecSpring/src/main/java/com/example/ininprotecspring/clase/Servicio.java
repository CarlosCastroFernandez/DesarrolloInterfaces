package com.example.ininprotecspring.clase;

import jakarta.persistence.*;

@Entity
@Table(name = "servicio")
public class Servicio {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String nombre;
  @Column(name = "horas_l_v")
  private Integer horasLV;
  @Column(name = "horas_s_d_f")
  private Integer horasSDF;
  private Double comision;

  private Double beneficio;
  @Column(name = "hora_total")
  private Integer horaTotal;
  private String municipio;

  public Servicio(Long id, String nombre, Integer horasLV, Integer horasSDF, Double comision, Double beneficio,Integer horaTotal,String municipio) {
    this.id = id;
    this.nombre = nombre;
    this.horasLV = horasLV;
    this.horasSDF = horasSDF;
    this.comision = comision;
    this.beneficio = beneficio;
    this.horaTotal=horaTotal;
    this.municipio=municipio;
  }

  public Servicio(String nombre, Integer horasLV, Integer horasSDF, Double comision, Double beneficio,Integer horaTotal,String municipio) {
    this.nombre = nombre;
    this.horasLV = horasLV;
    this.horasSDF = horasSDF;
    this.comision = comision;
    this.beneficio = beneficio;
    this.horaTotal=horaTotal;
    this.municipio=municipio;
    }
  public Servicio() {
  }

    public Long getId() {
    return id;
  }

  public String getMunicipio() {
    return municipio;
  }

  public void setMunicipio(String municipio) {
    this.municipio = municipio;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Integer getHorasLV() {
    return horasLV;
  }

  public Integer getHoraTotal() {
    return horaTotal;
  }

  public void setHoraTotal(Integer horaTotal) {
    this.horaTotal = horaTotal;
  }

  public void setHorasLV(Integer horasLV) {
    this.horasLV = horasLV;
  }

  public Integer getHorasSDF() {
    return horasSDF;
  }

  public void setHorasSDF(Integer horasSDF) {
    this.horasSDF = horasSDF;
  }

  public Double getComision() {
    return comision;
  }

  public void setComision(Double comision) {
    this.comision = comision;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }


  public Double getBeneficio() {
    return beneficio;
  }

  public void setBeneficio(Double beneficio) {
    this.beneficio = beneficio;
  }

    @Override
    public String toString() {
        return "Servicio{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", beneficio=" + beneficio +
                '}';
    }
}
