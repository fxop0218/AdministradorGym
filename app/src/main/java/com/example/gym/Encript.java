package com.example.gym;

import android.util.Base64;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Encript {

    private String encriptar (String datos, String password) throws Exception{

        SecretKeySpec secretKeySpec = generateKey(password);
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        byte[] datosEncriptadosBytes = cipher.doFinal(datos.getBytes());

        return Base64.encodeToString(datosEncriptadosBytes, Base64.DEFAULT);
    }

    private SecretKeySpec generateKey (String password) throws Exception{

        MessageDigest sha = MessageDigest.getInstance("SHA-256");
        byte[] key = password.getBytes(StandardCharsets.UTF_8);
        key = sha.digest(key);

        return new SecretKeySpec(key, "AES");
    }

    private String desencriptar (String datos, String password) throws Exception{

        SecretKeySpec secretKeySpec = generateKey(password);
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        byte[] datosDesencriptados = Base64.decode(datos, Base64.DEFAULT);
        byte[] datosDesencriptadosByte = cipher.doFinal(datosDesencriptados);

        return new String(datosDesencriptadosByte);
    }
}
