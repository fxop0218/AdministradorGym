package com.example.gym;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import com.example.gym.gymOwner.createAct_activity;

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
    private Date actualDate, selDate;
    private Button bSetActividad, bLogActiviy, bComingActivity;
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

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
        bSetActividad = view.findViewById(R.id.bNewActivity);
        bComingActivity = view.findViewById(R.id.bSeeUpcoming);
        cvCalendar = view.findViewById(R.id.cvCalendar);
        actualDate = new Date();


        cvCalendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2) {
                date = i2 + "/" + i1 + "/" +i;

                try {
                    selDate = sdf.parse(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Log.d("CalendarFragment", "onSelectedDayChange: date:"+ date);
                Toast.makeText(getContext(), date, Toast.LENGTH_SHORT).show();
            }
        });

        bComingActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selDate != null && !date.isEmpty()) {
                    if (!actualDate.before(selDate)) {

                        Toast.makeText(view.getContext(), "Ejecutado con exito", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getContext(), createAct_activity.class);
                        i.putExtra("day", date); // TODO tener en cuenta que no se puede clickar al boton cuando la fecha es inferior a la atual o igual.
                        startActivity(i);
                    } else {
                        Toast.makeText(view.getContext(), "Seleciona un dia posterior al de hoy", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(view.getContext(), "Error al selecionar un dia", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;

    }

    public void bGetActivity(View v) {

    }

    public void bSetReserva(View v) {

    }
}