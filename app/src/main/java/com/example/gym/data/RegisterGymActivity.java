package com.example.gym.data;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gym.Clases.Gym;
import com.example.gym.R;
import com.example.gym.pojos.PojosClass;

import java.text.SimpleDateFormat;

public class RegisterGymActivity extends AppCompatActivity {
    private EditText etGymID, etCity, etPostalCode, etHoraA, etHoraC;
    private Button bNextStep;
    private SimpleDateFormat df = new SimpleDateFormat("HH:mm");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_gym);

        //Declare all the EditText
        etGymID = findViewById(R.id.etIdGym);
        etCity = findViewById(R.id.etCiudad);
        etPostalCode = findViewById(R.id.etCodigoPostal);
        etHoraA = findViewById(R.id.etHoraA);
        etHoraC = findViewById(R.id.etHoraC);
        bNextStep = findViewById(R.id.bNextStep);

        //Listeners
        etGymID.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (RegisterComFunctions.is_not_correct(etGymID,8)) {
                    etGymID.setError("El id del gimnasio tiene que tener 8 numeros");
                    bNextStep.setEnabled(setRegisterEnabled());
                }
            }
        });

        etCity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (RegisterComFunctions.is_not_correct(etCity, 5, 30)) {
                    etCity.setError("El nombre de la ciudad tiene que tener entre 5 y 30 letras");
                    bNextStep.setEnabled(setRegisterEnabled());
                }
            }
        });

        etPostalCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (RegisterComFunctions.is_not_correct(etPostalCode, 5)) {
                    etPostalCode.setError("El codigo postal tiene que tener 5 numeros");
                    bNextStep.setEnabled(setRegisterEnabled());
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
                if (etHoraA.getText().toString() != null) bNextStep.setEnabled(setRegisterEnabled());
            }
        });

        etHoraC.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (etHoraC.getText().toString() != null) bNextStep.setEnabled(setRegisterEnabled());
            }
        });
    }

    /**
     * Pasa al siguinte paso cuando todos los campos estan rellenados de forma correcta
     *
     * @param v
     */
    public void bNextStep (View v) {
        //TODO Guardar aqui en la base de datos los archivos
        try {
            Gym g1 = new Gym(Integer.parseInt(etGymID.getText().toString()), etCity.getText().toString(), Integer.parseInt(etPostalCode.getText().toString()), df.parse(etHoraA.getText().toString()), df.parse(etHoraC.getText().toString()));
            PojosClass p1 = new PojosClass();
            p1.getGymDAO().setNewGym(g1);

            Intent i = new Intent(this, RegisterOwner_activity.class);
            i.putExtra("gymID", etGymID.getText().toString());
            startActivity(i);
        } catch (Exception e) {
            Toast.makeText(this, "Error al introducir el gimnasio a la base de datos", Toast.LENGTH_SHORT).show();
        }

    }

    public boolean setRegisterEnabled() {
        boolean correctLogin = true;
        if (RegisterComFunctions.is_not_correct(etGymID, 8)) {
            correctLogin = false;
        };
        if (RegisterComFunctions.is_not_correct(etCity, 5, 20)) {
            correctLogin = false;
        }
        if (RegisterComFunctions.is_not_correct(etPostalCode, 5)) correctLogin = false;
        if (etHoraA.getText().toString() == null) correctLogin = false;
        if (etHoraC.getText().toString() == null) correctLogin = false;
        return correctLogin;
    }
}