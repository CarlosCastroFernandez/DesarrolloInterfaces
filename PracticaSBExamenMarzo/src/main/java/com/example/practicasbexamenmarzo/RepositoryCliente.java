package com.example.practicasbexamenmarzo;

import com.example.practicasbexamenmarzo.clase.InformacionCliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepositoryCliente extends JpaRepository<InformacionCliente,Long> {

public List<InformacionCliente> getAllByTipoActividad(String actividad);
}
