package com.example.apiturismo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * La interfaz RepositoryHotel extiende JpaRepository para operaciones CRUD en la entidad Hotel.
 * Proporciona métodos personalizados y consultas JPQL relacionados con la entidad Hotel.
 */
public interface RepositoryHotel extends JpaRepository<Hotel, Long> {

    /**
     * Obtiene una lista de nombres e identificadores de hoteles.
     *
     * @return Lista de cadenas que contienen nombres e identificadores de hoteles.
     */
    @Query("SELECT h.nombre AS nombre, h.id AS id FROM Hotel h")
    List<String> hoteles();

    /**
     * Obtiene un hotel por su identificador.
     *
     * @param id Identificador del hotel.
     * @return El objeto Hotel asociado con el identificador proporcionado, o null si no se encuentra.
     */
    Hotel getHotelById(Integer id);

    /**
     * Obtiene una lista de hoteles asociados con un propietario específico.
     *
     * @param propietarioId Identificador del propietario.
     * @return Lista de hoteles asociados con el propietario proporcionado.
     */
    @Query("SELECT h FROM Hotel h WHERE h.propietarioId.id = :propietarioId")
    List<Hotel> getHotelByPropietarioId(@Param("propietarioId") Long propietarioId);

    /**
     * Obtiene una lista de hoteles fundados en un año específico.
     *
     * @param año Año de fundación de los hoteles.
     * @return Lista de hoteles fundados en el año proporcionado.
     */
    List<Hotel> getHotelByAnioFundado(Long año);

    /**
     * Obtiene un hotel por su dirección.
     *
     * @param direccion Dirección del hotel.
     * @return El objeto Hotel asociado con la dirección proporcionada, o null si no se encuentra.
     */
    Hotel getHotelByDireccion(String direccion);

}
