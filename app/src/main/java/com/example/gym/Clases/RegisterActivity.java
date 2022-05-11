package com.example.gym.Clases;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.gym.MainActivity;
import com.example.gym.R;

public class RegisterActivity extends Activity {
    private EditText etDni, etName, etSurname, etYear, etUserName, etPwd;
    private Button bRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etDni = findViewById(R.id.etDni);
        etName = findViewById(R.id.etName);
        etSurname = findViewById(R.id.etSurname);
        etUserName = findViewById(R.id.etUserName);
        etPwd = findViewById(R.id.etPwd);
        etYear = findViewById(R.id.etYear);
        bRegister = findViewById(R.id.bRegsiter);

        bRegister.setEnabled(false);

    }

    public void register(View view){
        //TODO guardar en la base de datos, si no se puede porque hay un usario on el mismo nombre te salta un error

        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public boolean setEnabled() {
        if (etName.getText().toString().length() < 5 || etName.getText().toString().length() > 20) {
            return false;
        } else if (etSurname.getText().toString().length() < 5 || etSurname.getText().toString().length() > 20) {
            return false;
        } else if (etYear.getText().toString().length() != 4) {
            return false;
        } else if (etUserName.getText().toString().length() < 5 || etUserName.getText().toString().length() > 30) {
            return false;
        } else if (etPwd.getText().toString().length() < 5 || etPwd.getText().toString().length() > 30) {
            return false;
        } else if (etDni.getText().toString().length() != 9) {
            return false;
        }

        return true;
    }
}