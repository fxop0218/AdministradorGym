package com.example.gym.pojos;

import com.example.gym.Clases.Actividad;
import com.google.firebase.firestore.FirebaseFirestore;

public interface ActividadesDAO {
    public void setActiviad(Actividad a1);
    public Actividad getActividad();
}
