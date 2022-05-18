package com.example.gym.pojos;

import android.util.Log;
import android.widget.Toast;

import com.example.gym.Clases.Gym;
import com.google.firebase.firestore.FirebaseFirestore;

public class GymDAOimp implements GymDAO{
    @Override
    public void setNewGym(Gym gym) {
        FirebaseFirestore db = FireConnection.getDb();
        db.collection("gym").document(gym.getIdGym()+"").set(gym);

    }

    @Override
    public Gym getGym(int idGym) {
        return null;
    }
}
