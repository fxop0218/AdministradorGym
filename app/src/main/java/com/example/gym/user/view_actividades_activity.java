package com.example.gym.user;

import androidx.annotation.ArrayRes;
import androidx.appcompat.app.AppCompatActivity;

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
import com.example.gym.pojos.PojosClass;

import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class view_actividades_activity extends AppCompatActivity {
    String day = "01/01/2000";
    private Button bClose;
    ArrayList<Actividad> activityArray = new ArrayList<>();
    ArrayList<Actividad> correctActivity = new ArrayList<>();

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
            activityArray = PojosClass.getActividadesDao().getGymActivity(UserSession.getUsuario().getIdGimnasios(), day,task -> {

                correctActivity = correctActivitys(activityArray);

            } );
        } catch (Exception e) {
            Toast.makeText(this, "No se ha encontrado ninguna actividad relacionada con el dia", Toast.LENGTH_SHORT).show();
        }

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

    private ArrayList<Actividad> correctActivitys(ArrayList<Actividad> allActivitys) {
        ArrayList<Actividad> correctActividades = new ArrayList<>();

        for (int i = 0; i < allActivitys.size(); i++) {
            if (allActivitys.get(i).getAforo() > allActivitys.get(i).getAforo_actual()) {
                correctActividades.add(allActivitys.get(i));
            }
        }
        return correctActividades;
    }
}