package com.example.ininprotecspring.repositorio;

import com.example.ininprotecspring.clase.PersonalBolsa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioAlumno extends JpaRepository<PersonalBolsa,Long> {
    public PersonalBolsa getByCorreo(String correo);

}
