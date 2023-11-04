package com.example.hibernatewithfx;

import com.example.hibernatewithfx.domain.usuario.User;
import lombok.Getter;
import lombok.Setter;

public class Session {
    @Getter @Setter
    private static User usuario;
}
