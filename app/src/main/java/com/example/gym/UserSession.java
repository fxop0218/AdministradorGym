package com.example.gym;

import com.example.gym.Clases.Usuario;

public class UserSession {
    private static Usuario usuario;

    public static Usuario getUsuario() {
        return usuario;
    }

    public static void setUsuario(Usuario actualSession) {
        usuario = actualSession;
    }
}
