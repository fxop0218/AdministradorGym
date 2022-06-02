package com.example.gym.data;

import android.widget.EditText;

public class dniValidator {
    public static boolean dni_validator(EditText etDni) {
        String dni = etDni.getText().toString();
        String mayusLetter = "";
        if (dni.length() != 9 || !Character.isLetter(dni.charAt(8))) {
            return false;
        }
        mayusLetter = (dni.substring(8)).toUpperCase();

        if (onlyNumbers(dni) && letraDNI(dni).equals(mayusLetter)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     * Mira si el dni introducido por el usuario es correcto
     * y es un dni existente, es decir, que la letra coincide con
     * el calculo de los numeros anteriores
     *
     *
     * @param dni
     * @return Si el dni es correcto devuelve true, si no lo es false
     */
    public static boolean onlyNumbers(String dni){
        int i,j=0;
        String numero="";
        String miDNI="";
        String[] unoNueve={"0","1","2","3","4","5","6","7","8","9"};
        for(i=0;i< dni.length()-1; i++) {
            numero = dni.substring(i, i + 1);

            for (j = 0; j < unoNueve.length; j++) {
                if (numero.equals(unoNueve[j])) {
                    miDNI += unoNueve[j];
                }
            }
        }
        if(miDNI.length() != 8){
            return false;
        } else {
            return true;
        }
    }

    public static String letraDNI(String dni){
        int miDNI=Integer.parseInt(dni.substring(0,8));
        int resto=0;
        String miletra="";
        String[] asignacionletra = {"T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "X","B","N", "J", "Z","S","Q","V", "H", "L", "C", "K","E"};
        resto = miDNI % 23;
        miletra = asignacionletra[resto];
        return miletra;
    }
}
