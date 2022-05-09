package com.example.gym.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import com.example.gym.R;

public class LoginActivity extends AppCompatActivity {
    private EditText etUser, etPwd;
    private Button btLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Declaramos los editText
        etPwd = findViewById(R.id.etPassword);
        etUser = findViewById(R.id.etUsname);

        //Declaramos los botones
        btLogin = findViewById(R.id.btLogin);
        //TODO declarar el boton de registrarse
        //TODO añadir el boton de registrarse al layOut

        //TODO crear la base de datos
    }
}
