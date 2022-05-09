package com.example.gym.Clases;

import java.util.Date;

public class GymEditor extends Persona {

    private Gym gym;

    public GymEditor(String nombre, String apellidos, String dni, Date dataNacimiento, String user, String password) {
        super(nombre, apellidos, dni, dataNacimiento, user, password);
    }

    public Gym getGym() {
        return gym;
    }

    public void setGym(Gym gym) {
        this.gym = gym;
    }

    public void get(){

    }
}
