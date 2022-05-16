package com.example.gym.pojos;

import com.example.gym.Clases.Usuario;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

public interface UsersDAO {
    public Usuario getUsuario(String userName, OnSuccessListener<Usuario> listener, OnFailureListener failure);
    public String getUserPwd(String userName);
}
