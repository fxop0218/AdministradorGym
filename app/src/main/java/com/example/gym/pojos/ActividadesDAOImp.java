package com.example.gym.pojos;

import com.example.gym.Clases.Actividad;
import com.google.firebase.FirebaseCommonRegistrar;
import com.google.firebase.firestore.FirebaseFirestore;

public class ActividadesDAOImp implements ActividadesDAO{
    @Override
    public void setActiviad(Actividad a1) {
        FirebaseFirestore db = FireConnection.getDb();
        db.collection("Actividades").document(a1.getIdActividad()+"").set(a1);
    }

    @Override
    public Actividad getActividad() {
        return null;
    }
}
