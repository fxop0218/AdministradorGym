package com.example.gym.pojos;
import com.example.gym.Clases.Gym;

public interface GymDAO {
    public void setNewGym(Gym gym);
    public Gym getGym(int idGym);

}
