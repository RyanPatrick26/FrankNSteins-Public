package com.example.install.franknsteins;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TableReserveFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TableReserveFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TableReserveFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    // Create the calendarView
    CalendarView tableCalendarView;

    // Create the test test file
    TextView testView;
    TextView timeTest;

    // Create the time picker
    TimePicker timePicker;

    // create the bookTable button
    Button bookTableButton;

    // ceate the time variables
    private int hour;
    private int minute;

    private OnFragmentInteractionListener mListener;

    public TableReserveFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TableReserveFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TableReserveFragment newInstance(String param1, String param2) {
        TableReserveFragment fragment = new TableReserveFragment();
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
        View view = inflater.inflate(R.layout.fragment_table_reserve, container, false);

        // link the calendarView to the one in the layout file
        tableCalendarView = (CalendarView) view.findViewById(R.id.tableCalendarView);
        // link the textview to the test one in the layout file
        testView = (TextView) view.findViewById(R.id.testView);
        timeTest = (TextView) view.findViewById(R.id.timeTest);
        // link the timepicker to the one in the layout file
        timePicker = (TimePicker) view.findViewById(R.id.timePicker);
        hour = timePicker.getHour();
        minute = timePicker.getMinute();
        timeTest.setText("Time: "+hour+":"+minute);
        // link the bookTable button to the one in the layout file
        bookTableButton = (Button) view.findViewById(R.id.bookTableButton);
        // create the date selection listener for the calendar
        tableCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            /**
             *
             *
             * @author Nicholas Allaire
             * @param calendarView
             * @param year
             * @param month
             * @param dayOfMonth
             */
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int year, int month, int dayOfMonth) {
                testView.setText("Selected date (mm-dd-yyyy):\n"
                        +month+"-"+dayOfMonth+"-"+year);
            }
        });

        // create the listener for the time picker
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            /**
             *
             *
             * @author Nicholas Allaire
             * @param timeView
             * @param hourOfDay
             * @param minute
             */
            @Override
            public void onTimeChanged(TimePicker timeView, int hourOfDay, int minute) {
                timeTest.setText("Time: "+hourOfDay+":"+minute);
            }
        });

        bookTableButton.setOnClickListener(new View.OnClickListener() {
            /**
             * 
             *
             * @author Nicholas Allaire
             * @param v
             */
            @Override
            public void onClick(View v) {

            }
        });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}
