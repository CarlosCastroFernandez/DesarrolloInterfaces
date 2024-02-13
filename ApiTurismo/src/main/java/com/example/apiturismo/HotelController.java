package com.example.apiturismo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.List;
import java.util.Optional;
/**
 * HotelController es un controlador de Spring que maneja las solicitudes relacionadas con la entidad Hotel.
 * Proporciona puntos finales para operaciones CRUD y para recuperar información sobre hoteles.
 *
 * La clase está anotada con anotaciones de Spring para indicar la ruta base ("/hoteles"),
 * y los repositorios y servicios necesarios se inyectan mediante la anotación @Autowired.
 */
@RestController
@RequestMapping("/hoteles")
public class HotelController {
    /**
     * Repositorio para acceder a la base de datos de hoteles.
     */
    @Autowired
private RepositoryHotel repositoryHotel;
    /**
     * Repositorio para acceder a la base de datos de clientes.
     */
    @Autowired
    private RepositoryCliente repositoryCliente;
    /**
     * Servicio de seguridad para validar tokens.
     */
    @Autowired
    private SecurityService service;
    /**
     * Repositorio para acceder a la base de datos de propietarios.
     */
    @Autowired
    private RepositoryPropietario repositoryOwner;
    /**
     * Punto final para obtener todos los hoteles.
     * Se requiere un correo electrónico y un token válidos para la autenticación.
     */
    @GetMapping("")
    public ResponseEntity<List<String>> getAllHotel( @RequestParam String token){

            if(service.validateTokerForUsers(token)){
                return new ResponseEntity<List<String>>(repositoryHotel.hoteles(), HttpStatus.OK);
            }
            if( service.validteTokenForOwner(token)){
                return new ResponseEntity<List<String>>(repositoryHotel.hoteles(), HttpStatus.OK);
            }

         return new ResponseEntity<List<String>>(HttpStatus.UNAUTHORIZED);
    }
    /**
     * Punto final para obtener un hotel por su ID.
     * Se requiere un correo electrónico, ID de hotel y un token válidos para la autenticación.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable Integer id,@RequestParam String token){

        if(service.validateTokerForUsers(token)){
            return new ResponseEntity<Hotel>((Hotel) repositoryHotel.getHotelById(id), HttpStatus.OK);
        }
        if( service.validteTokenForOwner(token)){
            return new ResponseEntity<Hotel>((Hotel) repositoryHotel.getHotelById(id), HttpStatus.OK);
        }

        return new ResponseEntity<Hotel>(HttpStatus.UNAUTHORIZED);
    }
    /**
     * Punto final para obtener lista de hoteles segun su propietario
     * Se requiere un correo electrónico, ID de propietario y un token válidos para la autenticación.
     */
    @GetMapping("/{idPropietario}")
    public ResponseEntity<List<Hotel>> getHotelByIdPropietario(@PathVariable Long idPropietario,@RequestParam String token){

        if(service.validateTokerForUsers(token)){
            return new ResponseEntity<List<Hotel>>(repositoryHotel.getHotelByPropietarioId(idPropietario), HttpStatus.OK);
        }
        if( service.validteTokenForOwner(token)){
            return new ResponseEntity<List<Hotel>>(repositoryHotel.getHotelByPropietarioId(idPropietario), HttpStatus.OK);
        }

        return new ResponseEntity<List<Hotel>>(HttpStatus.UNAUTHORIZED);
    }
    /**
     * Punto final para obtener lista de hoteles segun su año
     * Se requiere un correo electrónico, año de hotel y un token válidos para la autenticación.
     */

    @GetMapping("/{año}")
    public ResponseEntity<List<Hotel>>getHotelByAño( @PathVariable Long año,@RequestParam String token){

        if(service.validateTokerForUsers(token)){
            return new ResponseEntity<List<Hotel>>( repositoryHotel.getHotelByAnioFundado(año), HttpStatus.OK);
        }
        if( service.validteTokenForOwner(token)){
            return new ResponseEntity<List<Hotel>>(repositoryHotel.getHotelByAnioFundado(año), HttpStatus.OK);
        }

        return new ResponseEntity<List<Hotel>>(HttpStatus.UNAUTHORIZED);
    }
    /**
     *  para obtener un hotel en base a su año
     * Se requiere un correo electrónico, direccion de hotel y un token válidos para la autenticación.
     */
    @GetMapping("/{direccion}")
    public ResponseEntity<Hotel> getHotelByDireccion(@PathVariable String direccion,@RequestParam String token){

        if(service.validateTokerForUsers(token)){
            return new ResponseEntity<Hotel>((Hotel) repositoryHotel.getHotelByDireccion(direccion), HttpStatus.OK);
        }
        if( service.validteTokenForOwner(token)){
            return new ResponseEntity<Hotel>((Hotel) repositoryHotel.getHotelByDireccion(direccion), HttpStatus.OK);
        }

        return new ResponseEntity<Hotel>(HttpStatus.UNAUTHORIZED);
    }
    /**
     * Punto final para guardar un nuevo hotel.
     * Se requiere un correo electrónico válido, un objeto Hotel en el cuerpo de la solicitud y un token para la autenticación.
     */
@PostMapping("/saveHotel")
public ResponseEntity <Hotel> postHotel(@RequestBody Hotel hotel,@RequestParam String token){
        if(service.validteTokenForOwner(token)){
            Propietario pr=new Propietario();
            pr=repositoryOwner.getPropietarioByToken(token);
            hotel.setPropietarioId(pr);
            return new ResponseEntity<>(repositoryHotel.save(hotel),HttpStatus.OK);
        }else{
             return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

}
    /**
     * Punto final para actualizar el nombre de un hotel por su ID.
     * Se requiere un correo electrónico válido, ID de hotel, un objeto Hotel con el nuevo nombre en el cuerpo de la solicitud y un token.
     */
@PutMapping("/{id}")
public ResponseEntity<Hotel> putHotelById(@PathVariable Long id, @RequestBody Hotel hotel, @RequestParam String token){

        Hotel hotelito=new Hotel();
        Boolean boleano=false;
    Optional<Hotel>optionalHotel= repositoryHotel.findById(id);
    Propietario pr=repositoryOwner.getPropietarioByToken(token);
    if(pr==optionalHotel.get().getPropietarioId()){
        boleano=true;
    }else{
        boleano=false;
    }

    if(service.validteTokenForOwner(token)&&boleano){
        if(optionalHotel.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            hotelito=optionalHotel.get();
            hotelito.setNombre(hotel.getNombre());
            hotelito.setPrecioNoche(hotel.getPrecioNoche());
            hotelito.setAnioFundado(hotel.getAnioFundado());
            hotelito.setEstrellas(hotel.getEstrellas());
            hotelito.setNumHabitaciones(hotel.getNumHabitaciones());
            hotelito.setMetrosCuadrados(hotel.getMetrosCuadrados());
            hotelito.setDireccion(hotel.getDireccion());
            hotelito.setDescripcion(hotel.getDescripcion());

            return new ResponseEntity<>(repositoryHotel.save(hotelito),HttpStatus.OK);
        }
    }else{
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

}
    /**
     * Punto final para eliminar un hotel por su ID.
     * Se requiere un correo electrónico válido, ID de hotel y un token para la autenticación.
     */
@DeleteMapping("/{id}/deleteHotel")
public ResponseEntity<Hotel> deleteHotelById(@PathVariable Long id,@RequestParam String token){
        Hotel hotelBorrado=new Hotel();
        Boolean boleano=false;
        Propietario pr=repositoryOwner.getPropietarioByToken(token);
        hotelBorrado=repositoryHotel.findById(id).get();
        if(pr==hotelBorrado.getPropietarioId()&&pr!=null){
            boleano=true;
        }else{
            boleano=false;
        }
    if(service.validteTokenForOwner(token)&&repositoryHotel.existsById(id)&&boleano){
        hotelBorrado=repositoryHotel.findById(id).get();
        repositoryHotel.deleteById(id);
        return new ResponseEntity<Hotel>(hotelBorrado,HttpStatus.OK);
    }else if(!repositoryHotel.existsById(id)){
        return new ResponseEntity<Hotel>(HttpStatus.NOT_FOUND);
    }else{
        return new ResponseEntity<Hotel>(HttpStatus.UNAUTHORIZED);
    }
}
}
