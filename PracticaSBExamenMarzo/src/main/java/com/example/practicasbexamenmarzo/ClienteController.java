package com.example.practicasbexamenmarzo;

import com.example.practicasbexamenmarzo.clase.InformacionCliente;
import com.example.practicasbexamenmarzo.service.Servicio;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.ResourceBundle;

@RestController
@RequestMapping("")

public class ClienteController {

    @Autowired
    private RepositoryCliente repoCliente;
    @Autowired
    private Servicio capaService;

    @GetMapping("/getAll")
    private ResponseEntity<List<InformacionCliente>> getAll(@RequestParam String token){
        if(capaService.compruebaToken(token)){
            return new ResponseEntity<>(repoCliente.findAll(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

    }

    @GetMapping("clienteById/{id}")
    private ResponseEntity<InformacionCliente> getById(@PathVariable Long id){
        InformacionCliente cliente=repoCliente.findById(id).get();
        return new ResponseEntity<InformacionCliente>(cliente,HttpStatus.OK);

    }
    @GetMapping("clienteByActivity/{actividad}")
    private ResponseEntity<List<InformacionCliente>>getAllByActivity(@PathVariable String actividad){
      List <InformacionCliente> clientes=  repoCliente.getAllByTipoActividad(actividad);
      return new ResponseEntity<List<InformacionCliente>>(clientes,HttpStatus.OK);
    }
    @PostMapping("nuevoCLiente")
    private ResponseEntity<InformacionCliente>nuevo(@RequestBody InformacionCliente cliente){
        InformacionCliente clienteNuevo=cliente;
        repoCliente.save(clienteNuevo);
        return new ResponseEntity<>(clienteNuevo,HttpStatus.OK);
    }
    @DeleteMapping("deletreById/{id}")
    private ResponseEntity<InformacionCliente>deleteByid(@PathVariable Long id){
        if(repoCliente.existsById(id)){
            InformacionCliente cliente=repoCliente.findById(id).get();
            repoCliente.delete(cliente);
            return new ResponseEntity<>(cliente,HttpStatus.OK);
        }else{
           return new ResponseEntity<InformacionCliente>(HttpStatus.NOT_FOUND);
        }
    }

}
