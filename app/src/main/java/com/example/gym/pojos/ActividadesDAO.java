package com.example.gym.pojos;

import com.example.gym.Clases.Actividad;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

public interface ActividadesDAO {
    public void setActiviad(Actividad a1);
    public Actividad getActividad(int idActividad, OnSuccessListener<Actividad> listener, OnFailureListener failure);
    public Actividad[] getGymActivity(int gymID, , OnSuccessListener<Actividad> listener);
    public Actividad[] getUserActivitys(int idUser);
}
