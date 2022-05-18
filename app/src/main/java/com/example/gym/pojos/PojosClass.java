package com.example.gym.pojos;

public class PojosClass {
    public UsersDAOImp getUsuarioDAO () {
        return new UsersDAOImp();
    }
    public GymDAOimp getGymDAO() { return new GymDAOimp(); }
}
