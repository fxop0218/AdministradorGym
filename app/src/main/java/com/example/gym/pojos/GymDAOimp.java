package com.example.gym.pojos;

import android.util.Log;
import android.widget.Toast;

import com.example.gym.Clases.Gym;
import com.example.gym.Clases.Usuario;
import com.example.gym.UserSession;
import com.example.gym.data.ComFunctions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class GymDAOimp implements GymDAO{

    @Override
    public void setNewGym(Gym gym) {
        FirebaseFirestore db = FireConnection.getDb();
        db.collection(ComFunctions.GYM).document(gym.getIdGym()+"").set(gym);

    }

    @Override
    public Gym getGym(int idGym) throws Exception {
        final Gym[] gym = new Gym[1];
        FirebaseFirestore db = FireConnection.getDb();
        DocumentReference docRef = db.collection(ComFunctions.GYM).document(UserSession.getUsuario().getUser());
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                gym[0] = documentSnapshot.toObject(Gym.class);
            }
        });
        if (gym[0] != null) return gym[0];
        throw new Exception("No se ha encontrado ningun gymnasio con ese id");
    }
}
