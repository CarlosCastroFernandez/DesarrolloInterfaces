package com.example.demospringboot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class MainController {
    @GetMapping("/{nombre}")
    public String hola(@PathVariable String nombre){
        return "hola "+nombre;
    }
}
