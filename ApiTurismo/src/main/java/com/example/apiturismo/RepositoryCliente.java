package com.example.apiturismo;

import org.springframework.data.jpa.repository.JpaRepository;
/**
 * La interfaz RepositoryCliente extiende JpaRepository para operaciones CRUD en la entidad Cliente.
 * Proporciona métodos personalizados relacionados con la entidad Cliente.
 */

public interface RepositoryCliente extends JpaRepository<Cliente,Long> {

   /**
    * Comprueba la existencia de un cliente por su dirección de correo.
    *
    * @param correo Dirección de correo del cliente.
    * @return true si existe un cliente con la dirección de correo proporcionada, false de lo contrario.
    */
   Boolean existsClienteByCorreo(String correo);

   public Cliente getClienteByToken(String token);

   /**
    * Recupera un cliente por su dirección de correo.
    *
    * @param correo Dirección de correo del cliente.
    * @return El objeto Cliente asociado con la dirección de correo proporcionada, o null si no se encuentra.
    */
   Cliente getClienteByCorreo(String correo);
}
