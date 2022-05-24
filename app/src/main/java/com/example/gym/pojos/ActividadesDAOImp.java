package com.example.gym.pojos;

import androidx.annotation.NonNull;

import com.example.gym.Clases.Actividad;
import com.example.gym.Clases.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseCommonRegistrar;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ActividadesDAOImp implements ActividadesDAO{
    public static final String ACTIVIDADES = "Actividades";
    public static final String GYM_ID = "gymID";

    @Override
    public void setActiviad(Actividad a1) {
        FirebaseFirestore db = FireConnection.getDb();
        db.collection(ACTIVIDADES).document(a1.getIdActividad()+"").set(a1);
    }

    @Override
    public Actividad getActividad(int idActividad, OnSuccessListener<Actividad> listener,  OnFailureListener failure) {
        final Actividad[] act = new Actividad[1];
        FirebaseFirestore db = FireConnection.getDb();
        DocumentReference documentReference = db.collection(ACTIVIDADES).document(idActividad+"");
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                act[0] = documentSnapshot.toObject(Actividad.class);
                listener.onSuccess(act[0]);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @NotNull Exception e) {
                failure.onFailure(new Exception("Error al encontrar la actividad"));
            }
        });
        if (act[0] != null) return act[0];
        return null;
    }

    /*@Override
    public Actividad[] getGymActivity(int gymID, OnSuccessListener<Actividad> listener) {
        final Actividad[][] actArray = new Actividad[1][1];
        final Actividad[] activity = new Actividad[1];
        int i = 0;
        return null;
    }*/

    @Override
    public Actividad[] getUserActivitys(int idUser) {
        return new Actividad[0];
    }
}
