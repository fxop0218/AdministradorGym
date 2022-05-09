package com.example.gym.Clases;

public class Reserva {

    private Usuario usuario;
    private Actividad actividad;

    public Reserva(Usuario usuario, Actividad actividad) {
        this.usuario = usuario;
        this.actividad = actividad;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Actividad getActividad() {
        return actividad;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }
}
