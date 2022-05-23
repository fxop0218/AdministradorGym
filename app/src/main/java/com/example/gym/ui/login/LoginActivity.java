package com.example.gym.ui.login;

import android.app.Activity;

import com.example.gym.UserSession;
import com.example.gym.pojos.PojosClass;
import com.example.gym.databinding.ActivityLoginBinding;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Parcelable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gym.Clases.Usuario;
import com.example.gym.data.RegisterActivity;
import com.example.gym.MainActivity;
import com.example.gym.R;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;
    private ActivityLoginBinding binding;
    private boolean isOwner = false;
    private Usuario usr;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loginViewModel = new ViewModelProvider(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);

        final EditText usernameEditText = findViewById(R.id.username);
        final EditText passwordEditText = findViewById(R.id.password);
        final Button loginButton = binding.login;
        final ProgressBar loadingProgressBar = binding.loading;

        loginViewModel.getLoginFormState().observe(this, new Observer<LoginFormState>() {
            @Override
            public void onChanged(@Nullable LoginFormState loginFormState) {
                if (loginFormState == null) {
                    return;
                }
                loginButton.setEnabled(loginFormState.isDataValid());
                if (loginFormState.getUsernameError() != null) {
                    usernameEditText.setError(getString(loginFormState.getUsernameError()));
                }
                if (loginFormState.getPasswordError() != null) {
                    passwordEditText.setError(getString(loginFormState.getPasswordError()));
                }
            }
        });

        loginViewModel.getLoginResult().observe(this, new Observer<LoginResult>() {
            @Override
            public void onChanged(@Nullable LoginResult loginResult) {
                if (loginResult == null) {
                    return;
                }
                loadingProgressBar.setVisibility(View.GONE);
                if (loginResult.getError() != null) {
                    showLoginFailed(loginResult.getError());
                }
                if (loginResult.getSuccess() != null) {
                    updateUiWithUser(loginResult.getSuccess());
                }
                setResult(Activity.RESULT_OK);

                //Complete and destroy login activity once successful
                finish();
            }
        });

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                loginViewModel.loginDataChanged(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        };
        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    loginViewModel.login(usernameEditText.getText().toString(),
                            passwordEditText.getText().toString());
                }
                return false;
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            String pwd = "";
            Usuario usr = null;
            int idGym = 0;
            @Override
            public void onClick(View v) {
                loginButton.setEnabled(false);
                usr = PojosClass.getUsuarioDAO().getUsuario(usernameEditText.getText().toString(), (usuario -> {
                    //This catch throws a NullPointerException when don't get a User
                   try {
                       pwd = usuario.getPassword();
                       if (pwd.equals(passwordEditText.getText().toString())) {
                           UserSession.setUsuario(usuario);
                           isOwner = usuario.isGymOwner();
                           Toast.makeText(getApplicationContext(), "Bienvenido", Toast.LENGTH_SHORT).show();
                           loadingProgressBar.setVisibility(View.VISIBLE);
                           loginViewModel.login(usernameEditText.getText().toString(),
                                   passwordEditText.getText().toString());
                       } else {
                           Toast.makeText(getApplicationContext(), "La contraseña es incorrecta, intentalo de nuevo", Toast.LENGTH_SHORT).show();

                       }
                   } catch (NullPointerException e) {
                       loginButton.setEnabled(true);
                       Toast.makeText(getApplicationContext(), "Contraseña o usuario incorrecto", Toast.LENGTH_SHORT).show();
                   }
                }), (e -> {
                    try {
                        loginButton.setEnabled(true);
                        Toast.makeText(getApplicationContext(), "Contraseña o usuario incorrecto", Toast.LENGTH_SHORT).show();
                    } catch (Exception e1) {
                        loginButton.setEnabled(true);
                        Toast.makeText(getApplicationContext(), "Contraseña o usuario incorrecto", Toast.LENGTH_SHORT).show();
                    }
                }));
            }
        });
    }

    private void updateUiWithUser(LoggedInUserView model) {
        String welcome = getString(R.string.welcome) + model.getDisplayName();
        // TODO : initiate successful logged in experience
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("owner", isOwner); //binding.username.getText().toString()
        //intent.putExtra("usua", isOwner);
        startActivity(intent);
    }

    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString + "error", Toast.LENGTH_SHORT).show();
    }

    public void register(View view){

        Intent i = new Intent(this, RegisterActivity.class);
        startActivity(i);
    }
}