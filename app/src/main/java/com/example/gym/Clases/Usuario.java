package com.example.gym.Clases;

import java.util.Date;

public class Usuario{

    private String nombre;
    private String apellidos;
    private String dni;
    private int dataNacimiento;
    private String user;
    private String password;
    private int idGimnasios;
    private boolean gymOwner = false;

    //Contructor usuarios noramles
    public Usuario(String nombre, String apellidos, String dni, int dataNacimiento, String user, String password) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.dataNacimiento = dataNacimiento;
        this.user = user;
        this.password = password;
    }

    //Constructor dueños de un gimnasio
    public Usuario (String nombre, String apellidos, String dni, int dataNacimiento, String user, String password,int gymId, boolean gymOwner) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.dataNacimiento = dataNacimiento;
        this.user = user;
        this.password = password;
        this.idGimnasios = gymId;
        this.gymOwner = gymOwner;

    }
    //Getters and setters

    public int getGimnasios() {
        return idGimnasios;
    }



    /* TODO
    public void setGym (Gym gimnasio) {
        gimnasios[gimnasios.length] = gimnasio;
    }
     */

    public void setGimnasios(int gimnasios) {
        this.idGimnasios = gimnasios;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public int getDataNacimiento() {
        return dataNacimiento;
    }

    public void setDataNacimiento(int dataNacimiento) {
        this.dataNacimiento = dataNacimiento;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIdGimnasios() {
        return idGimnasios;
    }

    public void setIdGimnasios(int idGimnasios) {
        this.idGimnasios = idGimnasios;
    }

    public boolean isGymOwner() {
        return gymOwner;
    }

    public void setGymOwner(boolean gymOwner) {
        this.gymOwner = gymOwner;
    }
}
