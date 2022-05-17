package com.example.gym.data;

import android.widget.EditText;

import java.util.Calendar;

public class RegisterComFunctions {
    public static int actYear = Calendar.getInstance().get(Calendar.YEAR);

    public static boolean is_not_correct(EditText etValidator, int i, int i2) {
        if (etValidator.getText().toString().length() < i || etValidator.getText().toString().length() > i2) return true;
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
}
