package com.example.gym.Clases;

import java.util.Date;

public class Usuario extends Persona{

    private Gym[] gimnasios;
    private Reserva[] reservas;

    public Usuario(String nombre, String apellidos, String dni, Date dataNacimiento, String user, String password, Reserva[] reserva) {
        super(nombre, apellidos, dni, dataNacimiento, user, password, reserva);
    }


    //Getters and setters

    public Gym[] getGimnasios() {
        return gimnasios;
    }



    /* TODO
    public void setGym (Gym gimnasio) {
        gimnasios[gimnasios.length] = gimnasio;
    }
     */

    public void setGimnasios(Gym[] gimnasios) {
        this.gimnasios = gimnasios;
    }
}
