package com.example.hibernatewithfx;

import com.example.hibernatewithfx.domain.juego.Game;
import com.example.hibernatewithfx.domain.usuario.User;
import lombok.Getter;
import lombok.Setter;

public class Session {
    @Getter @Setter
    private static User usuario;
    @Getter @Setter
    private static Game currentGame;
}
