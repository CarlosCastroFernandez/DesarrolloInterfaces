package com.example.cesurspringboot.controllers;

import com.example.cesurspringboot.classes.Alumn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.cesurspringboot.repositories.RepositoryAlumn;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cesurapp/alumno")
public class ControllerAlumn {
    @Autowired
    private RepositoryAlumn repositoryAlumn;

    @GetMapping("")
    public List<Alumn> getAllAlumn(){
        return repositoryAlumn.findAll();
    }

}
