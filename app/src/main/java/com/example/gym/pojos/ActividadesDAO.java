package com.example.gym.pojos;

import com.example.gym.Clases.Actividad;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public interface ActividadesDAO {
    public void setActiviad(Actividad a1);
    public Actividad getActividad(int idActividad, OnSuccessListener<Actividad> listener, OnFailureListener failure);
    public ArrayList<Actividad> getGymActivity(int gymID,String actDate, OnCompleteListener<QuerySnapshot> listener) throws Exception;
    public Actividad[] getUserActivitys(int idUser);
    public Actividad getActividadById (int idActividad, OnCompleteListener<QuerySnapshot> listener);
}
