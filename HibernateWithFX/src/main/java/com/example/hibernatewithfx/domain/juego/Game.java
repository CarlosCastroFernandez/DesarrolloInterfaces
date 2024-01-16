package com.example.hibernatewithfx.domain.juego;

import com.example.hibernatewithfx.domain.usuario.User;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "coleccionjuegos")
public class Game implements Serializable {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
@Column(name="nombre")
  private String name;
@Column(name="anio")
  private Long year;
@Column(name="num_jugadores")
  private Long players;
@Column(name="categoria")
  private String category;
@Column(name="plataforma")
  private String plataform;
@Column(name="estudio")
  private String study;
@Column(name="empresa")
  private String enterprise;
@Column(name="formato")
  private String format;
@Column(name="estado_juego")
  private String gameStatus;
@Column(name="estado_caja")
  private String boxStatus;


@ManyToOne
@JoinColumn(name="usuario_id")
  private User user;

  @Override
  public String toString() {
    return "Game{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", year=" + year +
            ", players=" + players +
            ", category='" + category + '\'' +
            ", plataform='" + plataform + '\'' +
            ", study='" + study + '\'' +
            ", enterprise='" + enterprise + '\'' +
            ", format='" + format + '\'' +
            ", gameStatus='" + gameStatus + '\'' +
            ", boxStatus='" + boxStatus + '\'' +
            ", user=" + user.getUsername() +
            '}';
  }
  public Game(){
    this.players=1L;
    this.year=1980L;
  }
  protected static void merge(Game origen, Game destino) {
    destino.setName(origen.getName());
    destino.setFormat(origen.getFormat());
    destino.setGameStatus(origen.getGameStatus());
    destino.setBoxStatus(origen.getBoxStatus());
    destino.setEnterprise(origen.getEnterprise());
    destino.setPlataform(origen.getPlataform());
    destino.setCategory(origen.getCategory());
    destino.setStudy(origen.getStudy());
    destino.setYear(origen.getYear());
    destino.setPlayers(origen.getPlayers());
    destino.setUser(origen.getUser());
  }
}
