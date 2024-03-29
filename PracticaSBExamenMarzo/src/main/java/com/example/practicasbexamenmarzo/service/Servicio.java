package com.example.practicasbexamenmarzo.service;

import org.springframework.stereotype.Service;

@Service
public class Servicio {
    private final String token ="token";

    public Boolean compruebaToken(String tokencito){
        if(tokencito.equals(token)){
            return true;
        }else{
            return false;
        }
    }
}
