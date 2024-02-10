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

    /**
     * Valida el token para los usuarios/clientes.
     *
     * @param token  Token proporcionado para validar.
     * @param correo Correo del cliente para el cual se realiza la validación.
     * @return Booleano indicando si el token es válido para el cliente especificado.
     *         Devuelve null si el cliente no existe.
     */
    public Boolean validateTokerForUsers(String token, String correo) {
        Cliente cliente = repositoryCliente.getClienteByCorreo(correo);
        if (cliente == null) {
            return null;
        }
        return (token.equals(cliente.getToken()) && !token.equals(""));
    }

    /**
     * Valida el token para el propietario.
     *
     * @param token Token proporcionado para validar.
     * @return Booleano indicando si el token es válido para el propietario.
     */
    public Boolean validteTokenForOwner(String token) {
        return token.equals("ARXCVFGERAQ");
    }
}
