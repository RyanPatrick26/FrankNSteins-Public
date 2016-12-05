package com.example.install.franknsteins;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;


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

    // Create the test view
    TextView timeTest;

    // Create the time picker
    TimePicker timePicker;

    // create the bookTable button
    Button bookTableButton;

    // create the calendar variables
    private Integer calYear;
    private Integer calMonth;
    private Integer calDay;

    // create the time variables
    private Integer timeHour;
    private Integer timeMinute;

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
        // set the calendar variables to default selected day

        // link the textview to the test one in the layout file
        timeTest = (TextView) view.findViewById(R.id.timeTest);
        // link the timepicker to the one in the layout file
        timePicker = (TimePicker) view.findViewById(R.id.timePicker);
        timePicker.is24HourView();
        timeHour = timePicker.getHour();
        timeMinute = timePicker.getMinute();
        timeTest.setText("(24hr): "+timeHour+":"+timeMinute);
        // link the bookTable button to the one in the layout file
        bookTableButton = (Button) view.findViewById(R.id.bookTableButton);
        // create the date selection listener for the calendar
        tableCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            /**
             * When the user selects a different date on the calendar, a toast is displayed
             * with the selected date confirming the user made a selection.
             * The custom time variables are assigned the values of the calendar's day,
             * month, and year for later use with the book table button.
             *
             * @author Nicholas Allaire
             * @param calendarView
             * @param year
             * @param month
             * @param dayOfMonth
             */
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int year, int month, int dayOfMonth) {
                Toast.makeText(getActivity(),month+"/"+dayOfMonth+"/"+year+" selected",Toast.LENGTH_SHORT).show();
                calYear = year;
                calMonth = month;
                calDay = dayOfMonth;
            }
        });

        // create the listener for the time picker
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            /**
             * When the user selects a new time, the text of timeTest is set to the
             * selected time for app testing and to let the user know what time they
             * selected in a 24 hour format.
             * The selected hour a minute are then assigned to custom variables for
             * later use with the book table button
             *
             * @author Nicholas Allaire
             * @param timeView
             * @param hourOfDay
             * @param minute
             */
            @Override
            public void onTimeChanged(TimePicker timeView, int hourOfDay, int minute) {
                timeTest.setText("(24hr): "+hourOfDay+":"+minute);
                timeHour = hourOfDay;
                timeMinute = minute;
            }
        });

        bookTableButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Creates a new calendar instance and using the selected time and date variables
             * creates a new event to be stored in the user's device
             * If the user doesn't have the required software to run this intent, a
             * snackbar will display.
             *
             * @author Nicholas Allaire
             * @param v
             */
            @Override
            public void onClick(View v) {
                // Create a new calendar instance
                Calendar bookedTableCalendar = Calendar.getInstance();
                // set the calendar to the chosen date and time of the user
                // check if an initial date has been chosen, if not, display a snackbar
                if (calYear != null && calMonth != null && calDay != null) {

                    bookedTableCalendar.set(calYear,calMonth,calDay,timeHour,timeMinute);
                    // convert the chosen date to a long
                    long bookedTime = bookedTableCalendar.getTimeInMillis();

                    // create a new intent for setting the date event
                    Intent intent = new Intent(Intent.ACTION_INSERT);
                    intent.setData(CalendarContract.Events.CONTENT_URI);
                    // set the title for the event
                    intent.putExtra(CalendarContract.Events.TITLE, "Frank 'N' Steins Reservation");
                    // set the location for the event
                    intent.putExtra(CalendarContract.Events.EVENT_LOCATION, "Frank 'N' Steins Restaurant");
                    // set the booked time for the event
                    intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, bookedTime);

                    // Check to see if the user has the correct software installed on their device
                    if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                        startActivity(intent);
                    } else {
                        // if not, display a snackbar notifying them of this issue
                        if (!Locale.getDefault().getLanguage().contentEquals("fr")) {
                            Snackbar snackbar = Snackbar.make(getActivity().findViewById(android.R.id.content),
                                    "No installed software to complete process.", Snackbar.LENGTH_SHORT);
                            snackbar.show();
                        } else {
                            Snackbar snackbar = Snackbar.make(getActivity().findViewById(android.R.id.content),
                                    "Aucun logiciel installé pour terminer le processus.", Snackbar.LENGTH_SHORT);
                            snackbar.show();
                        }

                    }
                    // Display a confirmation toast.
                    Toast.makeText(getActivity(),"Reservation booked. Thanks! Let's mark it in your calendar",Toast.LENGTH_LONG).show();

                } else {
                    if (!Locale.getDefault().getLanguage().contentEquals("fr")) {
                        Snackbar snackbar = Snackbar.make(getActivity().findViewById(android.R.id.content),
                                "No date selected. Please select a date from the calendar and then book your table.", Snackbar.LENGTH_LONG);
                        snackbar.show();
                    } else {
                        Snackbar snackbar = Snackbar.make(getActivity().findViewById(android.R.id.content),
                                "Aucune date sélectionnée. Sélectionne une date dans le calendrier et réserve votre table.", Snackbar.LENGTH_LONG);
                        snackbar.show();
                    }
                }
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(getActivity(), PreferencesActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
