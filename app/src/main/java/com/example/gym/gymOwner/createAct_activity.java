package com.example.gym.gymOwner;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import com.example.gym.R;
import com.example.gym.data.ComFunctions;

import java.util.Locale;

public class createAct_activity extends AppCompatActivity {
    private Button bHoraA, bHoraC, bAdd;
    private EditText etHoraA, etHoraC, etActivityID, etGymID, etAforoMax;
    private int horaA, horaC, minuteA, minuteC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_act);
        bHoraC = findViewById(R.id.bSelectHoraC);
        bHoraA = findViewById(R.id.bSelectHoraA);
        bAdd = findViewById(R.id.bAdd);

        etHoraA = findViewById(R.id.etHoraA);
        etHoraC = findViewById(R.id.etHoraC);
        etActivityID = findViewById(R.id.etIdActividad);
        etGymID = findViewById(R.id.etIdGym);
        etAforoMax = findViewById(R.id.etAforoMax);

        /**
         * Seleciona la hora de Comienzo
         */

        bHoraA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        horaA = selectedHour;
                        minuteA = selectedMinute;
                        etHoraA.setText(String.format(Locale.getDefault(), "%02d:%02d", horaA, minuteA));
                    }
                };
                TimePickerDialog timePickerDialog = new TimePickerDialog(createAct_activity.this, AlertDialog.THEME_HOLO_DARK, onTimeSetListener, horaA, minuteA, true);
                timePickerDialog.setTitle("Seleciona la hora de apertura del gimnasio");
                timePickerDialog.show();
            }
        });

        bHoraC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        horaC = selectedHour;
                        minuteC = selectedMinute;
                        etHoraC.setText(String.format(Locale.getDefault(), "%02d:%02d", horaC, minuteC));
                    }
                };
                TimePickerDialog timePickerDialog = new TimePickerDialog(createAct_activity.this, AlertDialog.THEME_HOLO_DARK, onTimeSetListener, horaC, minuteC, true);
                timePickerDialog.setTitle("Seleciona la hora de apertura del gimnasio");
                timePickerDialog.show();
            }
        });

        etActivityID.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (ComFunctions.is_not_correct(etActivityID,8)) {
                    etActivityID.setError("El id de la actividad tiene que tener 8 numeros");
                    bAdd.setEnabled(setRegisterEnabled());
                }
            }
        });

        etGymID.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (ComFunctions.is_not_correct(etGymID,8)) {
                    etGymID.setError("El id de la actividad tiene que tener 8 numeros");
                    bAdd.setEnabled(setRegisterEnabled());
                }
            }
        });

        etAforoMax.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (Integer.parseInt(etAforoMax.getText().toString()) < 1 || Integer.parseInt(etAforoMax.getText().toString()) > 999) {
                    etAforoMax.setError("El aforo tiene que estar comprendido entre el 1 y el 999");
                    bAdd.setEnabled(setRegisterEnabled());
                }
            }
        });

        etHoraA.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!etHoraA.getText().toString().isEmpty()) {

                    //TODO hacer que no se puede ejecutar si es mayor a la hora de fin
                    etAforoMax.setError("El aforo tiene que estar comprendido entre el 1 y el 999");
                    bAdd.setEnabled(setRegisterEnabled());
                }
            }
        });

        etHoraA.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!etHoraC.getText().toString().isEmpty()) {
                    etAforoMax.setError("El aforo tiene que estar comprendido entre el 1 y el 999");
                    bAdd.setEnabled(setRegisterEnabled());
                }
            }
        });

    }
    //  etHoraA, etHoraC, etActivityID, etGymID, etAforoMax
    private boolean setRegisterEnabled() {
        boolean correctLogin = true;
        if (ComFunctions.is_not_correct(etGymID,8)) {
            correctLogin = false;
        };
        if (Integer.parseInt(etAforoMax.getText().toString()) < 1 || Integer.parseInt(etAforoMax.getText().toString()) > 999) {
            correctLogin = false;
        }
        if (ComFunctions.is_not_correct(etAforoMax, 5)) correctLogin = false;
        correctLogin = ComFunctions.isCorrectHour(etHoraA.getText().toString(), etHoraC.getText().toString());
        return correctLogin;

    }
//TODO no funciona

    public void dataPickerDialogHoraC (View view) {
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

    public void dataPickerDialogHoraA (View view) {
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

    private void isCorrectHour(String horaA, String horaC) {

    }
}