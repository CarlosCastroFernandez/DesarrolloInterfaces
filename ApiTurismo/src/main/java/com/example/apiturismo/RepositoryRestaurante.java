package com.example.apiturismo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * La interfaz RepositoryRestaurante extiende JpaRepository para operaciones CRUD en la entidad Restaurante.
 * Proporciona métodos personalizados para obtener información específica sobre los restaurantes.
 */
public interface RepositoryRestaurante extends JpaRepository<Restaurante, Long> {

    /**
     * Obtiene una lista de nombres e identificadores de todos los restaurantes.
     *
     * @return Lista de cadenas que representan nombres e identificadores de restaurantes.
     */
    @Query("select r.nombre, r.id from Restaurante r")
    List<String> restaurantes();

    /**
     * Obtiene un restaurante por su identificador.
     *
     * @param id Identificador del restaurante.
     * @return Objeto Restaurante correspondiente al identificador proporcionado.
     */
    Restaurante getRestauranteById(Long id);

    /**
     * Obtiene una lista de restaurantes asociados a un propietario por su identificador.
     *
     * @param propietarioRId Identificador del propietario del restaurante.
     * @return Lista de restaurantes asociados al propietario proporcionado.
     */
    @Query("SELECT r FROM Restaurante r WHERE r.propietarioRId.id = :propietarioRId")
    List<Restaurante> getRestauranteByPropietarioRId(@Param("propietarioRId") Long propietarioRId);

    /**
     * Obtiene una lista de restaurantes fundados en un año específico.
     *
     * @param año Año de fundación de los restaurantes.
     * @return Lista de restaurantes fundados en el año proporcionado.
     */
    List<Restaurante> getRestauranteByAnioFundado(Long año);

    /**
     * Obtiene un restaurante por su dirección.
     *
     * @param direccion Dirección del restaurante.
     * @return Objeto Restaurante correspondiente a la dirección proporcionada.
     */
    Restaurante getRestauranteByDireccion(String direccion);
}