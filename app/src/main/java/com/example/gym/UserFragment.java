package com.example.gym;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.lang.UScript;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.telephony.TelephonyManager;
import android.text.InputFilter;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gym.pojos.PojosClass;
import com.example.gym.pojos.UsersDAO;
import com.example.gym.ui.login.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.auth.User;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Button bSetGym;
    private TextView tvUser;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Button btCerrarSesion;
    private String gola;

    public UserFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UserFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UserFragment newInstance(String param1, String param2) {
        UserFragment fragment = new UserFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);


        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_user, container, false);
        bSetGym = v.findViewById(R.id.bAltaGym);
        btCerrarSesion = v.findViewById(R.id.cerrar_sesion);
        tvUser = v.findViewById(R.id.tvUsername);

        tvUser.setText("PERFIL DE " + UserSession.getUsuario().getUser());

        btCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(v.getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        /**
         * Abre un dialogo con el que puedes añadir una id de gimnasio
         * En caso de ser admin, no te permite cambiarlo
         * Si no tiene la longitud correcta te salta un error
         * Si no exite te salta un error
         *
         */
        bSetGym.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(v.getContext());
                alertBuilder.setTitle("Adjunta el ID de tu gimnasio (8 numeros)");
                final EditText etGymID = new EditText(v.getContext());
                etGymID.setInputType(InputType.TYPE_CLASS_NUMBER);
                etGymID.setFilters(new InputFilter[] {new InputFilter.LengthFilter(8) }); // Se añade un maximo de numeros al edit text
                alertBuilder.setView(etGymID);
                alertBuilder.setCancelable(true);
                alertBuilder.setPositiveButton("Acceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (!etGymID.getText().toString().isEmpty() && etGymID.getText().toString().length() == 8) {
                            try {
                                PojosClass.getGymDAO().getGym(Integer.parseInt(etGymID.getText().toString()), (gym ->{
                                    try {
                                        PojosClass.getUsuarioDAO().addGym(Integer.parseInt(etGymID.getText().toString()));
                                        Toast.makeText(v.getContext(), "Te has insctito con exito a " + gym.getIdGym(), Toast.LENGTH_SHORT).show();
                                        UserSession.getUsuario().setIdGimnasios(Integer.parseInt(etGymID.getText().toString())); // Se añade la nueva id de gimnasion en la session de usuario actual

                                    } catch (Exception e) {
                                        Toast.makeText(v.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }), (failure -> {
                                    Toast.makeText(v.getContext(), failure.getMessage(), Toast.LENGTH_SHORT).show();
                                }));
                            } catch (Exception e) {
                                Toast.makeText(v.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getContext(), "Introduce una id correct ", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("Cerrar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(v.getContext(), "Se ha cancelado la operación", Toast.LENGTH_SHORT).show();
                    }
                });
                alertBuilder.show();
            }
        });

        return v;
    }
}