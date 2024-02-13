package com.example.apiturismo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * El servicio SecurityService proporciona funciones relacionadas con la validación de tokens de seguridad.
 */
@Service
public class SecurityService {

    @Autowired
    private RepositoryCliente repositoryCliente;
    @Autowired RepositoryPropietario repositoryPropietario;

    /**
     * Valida el token para los usuarios/clientes.
     *
     * @param token  Token proporcionado para validar.
     *
     * @return Booleano indicando si el token es válido para el cliente especificado.
     *         Devuelve null si el cliente no existe.
     */
    public Boolean validateTokerForUsers(String token) {
        Cliente cliente = repositoryCliente.getClienteByToken(token);
        if (cliente == null) {
            return false;
        }else{
            return true;
        }
    }

    /**
     * Valida el token para el propietario.
     *
     * @param token Token proporcionado para validar.
     * @return Booleano indicando si el token es válido para el propietario.
     */
    public Boolean validteTokenForOwner(String token) {
        Propietario pr=repositoryPropietario.getPropietarioByToken(token);
        if(pr!=null){
            return true;
        }else{
            return false;
        }

    }
}
