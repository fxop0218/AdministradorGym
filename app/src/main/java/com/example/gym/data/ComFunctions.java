package com.example.gym.data;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.widget.EditText;
import android.widget.TimePicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ComFunctions {
    public static int actYear = Calendar.getInstance().get(Calendar.YEAR);
    public static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    public static SimpleDateFormat sdfMin = new SimpleDateFormat("HH:mm");
    //Constantes de Actividad
    public static final String ID_ACTIVIDAD = "actividad";
    public static final String USER_NAME = "actividad";
    public static final String ID_RESERVA = "idReserva";

    //-----------------------------------------------------------
    //Constantes de GYM
    public static final String GYM = "gym";

    //------------------------------------------------------------
    //Constantes de Registro
    public static final String RESERVA = "Reserva";


    public static boolean is_not_correct(EditText etValidator, int i, int i2) {
        if (etValidator.getText().toString().length() < i || etValidator.getText().toString().length() > i2)
            return true;
        return false;
    }

    public static boolean is_not_correct(EditText etValidator, int i) {
        if (etValidator.getText().toString().length() != i) return true;
        return false;
    }

    public static boolean is_not_correct_year(int year, EditText etYear) {
        if (is_not_correct(etYear, 4)) return true;
        if (year < actYear - 100 || year > actYear) return true;
        return false;
    }

    public static boolean isCorrectHour(String horaA, String horaC) {
            Date hDateA = strToDate(horaA);
            Date hDateC = strToDate(horaC);

            if (hDateA == null || hDateC == null) return false;
            if (hDateA.after(hDateC)) return false;
            return true;
    }

    /**
     * Transforma una String con un formato en concreto (HH:mm) en
     * un objeto de clase Date con la hora.
     *
     * @param hora
     * @return null si no es correcto el formato
     */
    public static Date strToDate(String hora) {
        try {
            return sdfMin.parse(hora);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * Transforma una string con un formato concreto (dd/mm/yyyy) en
     * un objeto Date.
     *
     * @param dateStr
     * @return  si no es correcto el formato
     */
    public static Date strToDateDay(String dateStr) {
        try {
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            return null;
        }
    }

    public static Date getActualDate() {
        return new Date();
    }
}
