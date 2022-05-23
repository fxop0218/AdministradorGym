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

    public static void dataPickerDialog (EditText etHoraA, Context context) {
        final int[] horaA = {0};
        final int[] minuteA = { 0 };
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                horaA[0] = selectedHour;
                minuteA[0] = selectedMinute;
                etHoraA.setText(String.format(Locale.getDefault(), "%02d:%02d", horaA[0], minuteA[0]));
            }
        };
        TimePickerDialog timePickerDialog = new TimePickerDialog(context, AlertDialog.THEME_HOLO_DARK, onTimeSetListener, horaA[0], minuteA[0], true);
        timePickerDialog.setTitle("Seleciona la hora de apertura del gimnasio");
        timePickerDialog.show();
    }

    public static boolean isCorrectHour(String horaA, String horaC) {
        try {
            Date hDateA = sdf.parse(horaA);
            Date hDateC = sdf.parse(horaC);

            if (hDateA == null || hDateC == null) return false;
            if (hDateA.after(hDateC) || hDateA.equals(hDateC)) return true;

        } catch (ParseException e) {
            return false;
        }
        return false;
    }
}
