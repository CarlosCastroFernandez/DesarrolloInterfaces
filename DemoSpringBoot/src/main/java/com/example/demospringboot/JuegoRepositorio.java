package com.example.demospringboot;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface JuegoRepositorio extends JpaRepository<Juego,Long> {
    public List<Juego> getJuegoByPlataforma(String plataforma);
    public List<Juego> getJuegosByAnioBefore(Integer anio);
    @Query("select p From Juego p where p.id=10")
    public Juego getJuego();
}
