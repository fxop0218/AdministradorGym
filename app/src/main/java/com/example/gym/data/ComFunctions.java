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
    public static Date strToDate(String hora) {
        try {
            return sdfMin.parse(hora);
        } catch (ParseException e) {
            return null;
        }
    }
}
