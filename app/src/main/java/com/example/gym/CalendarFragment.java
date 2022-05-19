package com.example.gym;

import android.app.AlertDialog;
import android.app.usage.UsageEvents;
import android.content.DialogInterface;
import android.graphics.Color;
import android.media.metrics.Event;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CalendarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CalendarFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private CalendarView cvCalendar;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private String selectedDate;

    private static Date actualDate = new Date();
    private Date selDate;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String date;

    public CalendarFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CalendarFragment.
     */

    // TODO: Rename and change types and number of parameters
    public static CalendarFragment newInstance(String param1, String param2) {
        CalendarFragment fragment = new CalendarFragment();
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

    /**
     * Inflate the XML layout for the Fragment in this callback.
     * Create a onSelectDayChanged() to get the day selected by the user
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     *
     * @return view
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);
        cvCalendar = (CalendarView) view.findViewById(R.id.cvCalendar);
        cvCalendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2) {
                date = i2 + "/" + i1 + "/" +i;

                try {
                    actualDate = sdf.parse(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Log.d("CalendarFragment", "onSelectedDayChange: date:"+ date);
                Toast.makeText(getContext(), date , Toast.LENGTH_SHORT).show();
            }
        });


        return view;

    }

    public void bGetActivity(View v) {

    }

    public void bSetReserva(View v) {

    }

    public void bSetActivity(View v) {
        if (selDate != null) {
            if (!selDate.before(actualDate)) {
                AlertDialog.Builder myDialog = new AlertDialog.Builder(getContext());
                myDialog.setTitle("Actividad");

                myDialog.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getContext(), "Funciona flameton", Toast.LENGTH_SHORT).show();
                    }
                });
                myDialog.show();
            } else {
                Toast.makeText(v.getContext(), "No puedes crear una actividad para un dia anterior" +
                        "al actual", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(v.getContext(), "Seleciona una fecha", Toast.LENGTH_SHORT).show();
        }

    }

}