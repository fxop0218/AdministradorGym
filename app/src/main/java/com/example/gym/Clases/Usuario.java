package com.example.gym.Clases;

import java.util.Date;

public class Usuario extends Persona{

    private Gym[] gimnasios;

    public Usuario(String nombre, String apellidos, String dni, Date dataNacimiento, String user, String password) {
        super(nombre, apellidos, dni, dataNacimiento, user, password);
    }

    public Gym[] getGimnasios() {
        return gimnasios;
    }

    public void setGimnasios(Gym[] gimnasios) {
        this.gimnasios = gimnasios;
    }
}
