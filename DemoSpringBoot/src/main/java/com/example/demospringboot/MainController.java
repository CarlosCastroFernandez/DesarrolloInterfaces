package com.example.demospringboot;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class MainController {
    @Autowired
    private JuegoRepositorio repositorio;
    @GetMapping("/")
    public List<Juego> getAll(){

        return repositorio.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Juego> getOne(@PathVariable Long id){
        if(repositorio.existsById(id)){
            return new ResponseEntity<Juego>(repositorio.findById(id).get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<Juego>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/plataforma/{plataforma}")
    public List<Juego>getByPlataforma(@PathVariable String plataforma){
      return   repositorio.getJuegoByPlataforma(plataforma);
    }
    @GetMapping("/anteriores/{anio}")
    public List<Juego>getAnteriores(@PathVariable Integer anio){
        return   repositorio.getJuegosByAnioBefore(anio);
    }
    @GetMapping("/primero")
    public Juego primero(){
        return repositorio.getJuego();
    }
}
