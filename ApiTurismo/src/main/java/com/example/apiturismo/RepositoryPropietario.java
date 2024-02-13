package com.example.apiturismo;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * La interfaz RepositoryPropietario extiende JpaRepository para operaciones CRUD en la entidad Propietario.
 * Proporciona un método personalizado para verificar la existencia de un propietario por su correo electrónico.
 */
public interface RepositoryPropietario extends JpaRepository<Propietario, Long> {

    /**
     * Verifica la existencia de un propietario por su correo electrónico.
     *
     * @param correo Correo electrónico del propietario a verificar.
     * @return true si existe un propietario con el correo electrónico proporcionado, false de lo contrario.
     */
    Boolean existsPropietarioByCorreo(String correo);
    Propietario getPropietarioByToken(String token);
}
