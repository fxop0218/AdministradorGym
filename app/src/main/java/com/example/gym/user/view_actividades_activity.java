package com.example.gym.user;

import androidx.annotation.ArrayRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView;

import com.example.gym.Clases.Actividad;
import com.example.gym.Clases.Reserva;
import com.example.gym.R;
import com.example.gym.UserSession;
import com.example.gym.data.ComFunctions;
import com.example.gym.pojos.FireConnection;
import com.example.gym.pojos.PojosClass;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class view_actividades_activity extends AppCompatActivity {
    String day = "01/01/2000";
    private Button bClose;
    List<Actividad> activityArray = new ArrayList<>();
    List<Actividad> correctActivity = new ArrayList<>();
    //ArrayList<Actividad> activityArray = new ArrayList<>();
    //ArrayList<Actividad> correctActivity = new ArrayList<>();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference activityRef = db.collection(ComFunctions.ACTIVIDADES);

    ListView lvActividades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_actividades);
        bClose = findViewById(R.id.bClose);
        Intent i = getIntent();
        lvActividades = findViewById(R.id.lv_Actividades);
        day = i.getStringExtra("day");

        try {
            prueba(UserSession.getUsuario().getIdGimnasios(), day);
        } catch (Exception e) {
            e.printStackTrace();
        }
/*
        try {
            activityArray = PojosClass.getActividadesDao().getGymActivity(UserSession.getUsuario().getIdGimnasios(), day ,task -> {
                correctActivity = correctActivitys(activityArray);
            }, (e -> {
                // Cuando no hay actividades o no estas apuntado a un gymnasio
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }) );
        } catch (Exception e) {
            Toast.makeText(this, "No se ha encontrado ninguna actividad relacionada con el dia", Toast.LENGTH_SHORT).show();
        }

 */

        ArrayAdapter<Actividad> arrayAdapter = new ArrayAdapter<Actividad>(this, R.layout.activity_view_actividades, R.id.textView, correctActivity);
        lvActividades.setAdapter(arrayAdapter);

        lvActividades.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //TODO comprobar si el esta completo la actividad
                //TODO apuntarse a la actividad
                Actividad act = correctActivity.get(i);
                ArrayList<Reserva> reservaList = PojosClass.getReservaDao().getReservaByUserName(UserSession.getUsuario().getUser());

                boolean notAlisted = true;
                int r = 0;
                while (r < reservaList.size() && !notAlisted) {
                    if (act.getIdActividad() == reservaList.get(r).getActividad()) {
                        notAlisted = false;
                    }
                    r++;
                }

                if (notAlisted) {
                    try {
                        PojosClass.getReservaDao().addReserva(new Reserva(UserSession.getUsuario().getUser(), act.getIdActividad() ,UserSession.getUsuario().getUser() + "" + act.getIdActividad()));
                        act.sumAforo_actual();
                        PojosClass.getActividadesDao().setActiviad(act); //Se actualiza la actividad

                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Error al asignarte a la acividad", Toast.LENGTH_SHORT).show();
                    }
                } else { //En caso de estar apuntado en la actividad, muestra un mensaeje;
                    Toast.makeText(getApplicationContext(), "Ya estas apuntado en esta actividad", Toast.LENGTH_SHORT).show();
                }
            }
        });

        bClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private List<Actividad> correctActivitys(List<Actividad> allActivitys) {
        ArrayList<Actividad> correctActividades = new ArrayList<>();

        for (int i = 0; i < allActivitys.size(); i++) {
            if (allActivitys.get(i).getAforo() > allActivitys.get(i).getAforo_actual()) {
                correctActividades.add(allActivitys.get(i));
            }
        }
        return correctActividades;
    }

    public void prueba(int gymID, String actDate) throws Exception {
        activityRef.limit(3).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List<Actividad> actividadesL = new ArrayList<>();
                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                    Actividad act = documentSnapshot.toObject(Actividad.class);
                    act.setIdActividad(Integer.parseInt(documentSnapshot.getId()));
                }
                activityArray = actividadesL;
            }
        });
    }
}