package com.example.gym.gymOwner;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.TimePicker;

import com.example.gym.R;
import com.example.gym.data.ComFunctions;

import java.util.Locale;

public class ActCreationActivity extends AppCompatActivity {
    private Button bHoraA, bHoraC;
    private EditText etHoraA, etHoraC;
    private int horaA, horaC, minuteA, minuteC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_creation);
        bHoraC = findViewById(R.id.bSelectHoraC);
        bHoraA = findViewById(R.id.bSelectHoraA);

        etHoraA = findViewById(R.id.etHoraA);
        etHoraC = findViewById(R.id.etHoraC);

        /**
         * Seleciona la hora de Comienzo
         */

    }
//TODO no funciona

    public void dataPickerDialogHoraC (View v) {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                horaC = selectedHour;
                minuteC = selectedMinute;
                etHoraC.setText(String.format(Locale.getDefault(), "%02d:%02d", horaC, minuteC));
            }
        };
        TimePickerDialog timePickerDialog = new TimePickerDialog(getApplicationContext(), AlertDialog.THEME_HOLO_DARK, onTimeSetListener, horaC, minuteC, true);
        timePickerDialog.setTitle("Seleciona la hora de apertura del gimnasio");
        timePickerDialog.show();
    }
//TODO no funciona

    public void dataPickerDialogHoraA (View v) {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                horaA = selectedHour;
                minuteA = selectedMinute;
                etHoraA.setText(String.format(Locale.getDefault(), "%02d:%02d", horaA, minuteA));
            }
        };
        TimePickerDialog timePickerDialog = new TimePickerDialog(getApplicationContext(), AlertDialog.THEME_HOLO_DARK, onTimeSetListener, horaA, minuteA, true);
        timePickerDialog.setTitle("Seleciona la hora de apertura del gimnasio");
        timePickerDialog.show();
    }
}