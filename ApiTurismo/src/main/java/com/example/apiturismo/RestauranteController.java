package com.example.apiturismo;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * El controlador RestauranteController maneja las solicitudes relacionadas con la entidad Restaurante.
 * Responde a las operaciones CRUD y proporciona métodos personalizados para obtener información específica sobre restaurantes.
 */
@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    @Autowired
    private RepositoryRestaurante repositoryRestaurante;
    @Autowired
    private RepositoryCliente repositoryCliente;
    @Autowired
    private SecurityService service;
    @Autowired
    private RepositoryPropietario repositoryOwner;

    /**
     * Obtiene una lista de nombres e identificadores de todos los restaurantes.
     *
     *
     * @param token Token de autenticación del usuario.
     * @return ResponseEntity con una lista de cadenas que representan nombres e identificadores de restaurantes.
     */
    @GetMapping("")
    public ResponseEntity<List<String>> getAllRestaurantes(@RequestParam String token) {

        if (service.validateTokerForUsers(token)) {
            return new ResponseEntity<>(repositoryRestaurante.restaurantes(), HttpStatus.OK);
        }
        if (service.validteTokenForOwner(token)) {
            return new ResponseEntity<>(repositoryRestaurante.restaurantes(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    /**
     * Obtiene un restaurante por su identificador.
     *

     * @param id Identificador del restaurante.
     * @param token Token de autenticación del usuario.
     * @return ResponseEntity con el objeto Restaurante correspondiente al identificador proporcionado.
     */
    @GetMapping("/restauranteById/{id}")
    public ResponseEntity<Restaurante> getRestauranteById(@PathVariable Long id, @RequestParam String token) {

        if ( service.validateTokerForUsers(token)) {
            return new ResponseEntity<>(repositoryRestaurante.getRestauranteById(id), HttpStatus.OK);
        }
        if (service.validteTokenForOwner(token)) {
            return new ResponseEntity<>(repositoryRestaurante.getRestauranteById(id), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    /**
     * Obtiene una lista de restaurantes asociados a un propietario por su identificador.
     *

     * @param idPropietarioR Identificador del propietario del restaurante.
     * @param token Token de autenticación del usuario.
     * @return ResponseEntity con una lista de restaurantes asociados al propietario proporcionado.
     */
    @GetMapping("/restauranteByPropietarioId/{idPropietarioR}")
    public ResponseEntity<List<Restaurante>> getRestauranteByPropietarioId( @PathVariable Long idPropietarioR, @RequestParam String token) {

        if ( service.validateTokerForUsers(token)) {
            return new ResponseEntity<>(repositoryRestaurante.getRestauranteByPropietarioRId(idPropietarioR), HttpStatus.OK);
        }
        if (service.validteTokenForOwner(token)) {
            return new ResponseEntity<>(repositoryRestaurante.getRestauranteByPropietarioRId(idPropietarioR), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    /**
     * Obtiene una lista de restaurantes fundados en un año específico.
     *
     * @param año Año de fundación de los restaurantes.
     * @param token Token de autenticación del usuario.
     * @return ResponseEntity con una lista de restaurantes fundados en el año proporcionado.
     */
    @GetMapping("/restaurantesByAño/{año}")
    public ResponseEntity<List<Restaurante>> restauranteByAño(@PathVariable Long año, @RequestParam String token) {

        if (service.validateTokerForUsers(token)) {
            return new ResponseEntity<>(repositoryRestaurante.getRestauranteByAnioFundado(año), HttpStatus.OK);
        }
        if (service.validteTokenForOwner(token)) {
            return new ResponseEntity<>(repositoryRestaurante.getRestauranteByAnioFundado(año), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    /**
     * Obtiene un restaurante por su dirección.
     *

     * @param direccion Dirección del restaurante.
     * @param token Token de autenticación del usuario.
     * @return ResponseEntity con el objeto Restaurante correspondiente a la dirección proporcionada.
     */
    @GetMapping("/restauranteByDireccion/{direccion}")
    public ResponseEntity<Restaurante> restauranteByDireccion( @PathVariable String direccion, @RequestParam String token) {
        if (service.validateTokerForUsers(token)) {
            return new ResponseEntity<>(repositoryRestaurante.getRestauranteByDireccion(direccion), HttpStatus.OK);
        }
        if (service.validteTokenForOwner(token)) {
            return new ResponseEntity<>(repositoryRestaurante.getRestauranteByDireccion(direccion), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    /**
     * Guarda un nuevo restaurante.
     *

     * @param restaurante Objeto Restaurante a guardar.
     * @param token Token de autenticación del usuario.
     * @return ResponseEntity con el Restaurante guardado.
     */
    @PostMapping("/saveRestaurante")
    public ResponseEntity<Restaurante> saveRestaurante(@RequestBody Restaurante restaurante, @RequestParam String token) {
        if (service.validteTokenForOwner(token)) {
            Propietario pr=new Propietario();
            pr=repositoryOwner.getPropietarioByToken(token);
            restaurante.setPropietarioRId(pr);
            return new ResponseEntity<>(repositoryRestaurante.save(restaurante), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * Actualiza el nombre de un restaurante por su identificador.
     *

     * @param id Identificador del restaurante a actualizar.
     * @param restaurante Objeto Restaurante con el nuevo nombre.
     * @param token Token de autenticación del usuario.
     * @return ResponseEntity con el Restaurante actualizado.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Restaurante> updateRestauranteByName(@PathVariable Long id, @RequestBody Restaurante restaurante, @RequestParam String token) {
        Restaurante restauranteNuevo = new Restaurante();
        Optional<Restaurante> optionalRestaurante = repositoryRestaurante.findById(id);
        if (service.validteTokenForOwner(token)) {
            if (optionalRestaurante.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                restauranteNuevo = optionalRestaurante.get();
                restauranteNuevo.setNombre(restaurante.getNombre());
                restauranteNuevo.setDescripcion(restaurante.getDescripcion());
                restauranteNuevo.setAnioFundado(restaurante.getAnioFundado());
                restauranteNuevo.setDireccion(restaurante.getDireccion());
                restauranteNuevo.setTipo(restaurante.getTipo());
                restauranteNuevo.setValoracion(restaurante.getValoracion());

                return new ResponseEntity<>(repositoryRestaurante.save(restauranteNuevo), HttpStatus.OK);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * Elimina un restaurante por su identificador.
     *

     * @param id Identificador del restaurante a eliminar.
     * @param token Token de autenticación del usuario.
     * @return ResponseEntity con el Restaurante eliminado.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Restaurante> deleteRestauranteById(@PathVariable Long id, @RequestParam String token) {
        Restaurante restauranteBorrado = new Restaurante();
        if (service.validteTokenForOwner(token) && repositoryRestaurante.existsById(id)) {
            restauranteBorrado = repositoryRestaurante.findById(id).get();
            try{
                repositoryRestaurante.deleteById(id);
            }catch(Exception e){
                e.printStackTrace();
            }

            return new ResponseEntity<>(restauranteBorrado, HttpStatus.OK);
        } else if (!repositoryRestaurante.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}