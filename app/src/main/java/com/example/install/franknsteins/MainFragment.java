package com.example.install.franknsteins;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MainFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    // Create the navigation buttons
    private Button menuButton;
    private Button contactButton;
    private Button reserveTableButton;
    private Button billEstimatorButton;
    private Button buildaFrankButton;

    // create the fragment listener
    private OnFragmentInteractionListener mListener;

    // Create the fragment manager
    FragmentManager fragMan;

    public MainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
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
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        // Link the buttons to the corresponding button IDs
        menuButton = (Button) view.findViewById(R.id.menuButton);
        contactButton = (Button) view.findViewById(R.id.contactButton);
        reserveTableButton = (Button) view.findViewById(R.id.reserveTableButton);
        billEstimatorButton = (Button) view.findViewById(R.id.billEstimatorButton);
        buildaFrankButton = (Button) view.findViewById(R.id.buildafrankButton);

        // Set the event listeners for the buttons
        menuButton.setOnClickListener(menuButtonListener);
        contactButton.setOnClickListener(contactButtonListener);
        reserveTableButton.setOnClickListener(reserveTableButtonListener);
        billEstimatorButton.setOnClickListener(billEstimatorButtonListener);
        buildaFrankButton.setOnClickListener(buildaFrankButtonListener);

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

    /**
     * On Click Listener for the Menu Button
     * Switches to the MenuFragment
     */
    public View.OnClickListener menuButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // Create the instantiate the fragment manager
            fragMan = getActivity().getSupportFragmentManager();
            // Create the fragment transaction
            FragmentTransaction fragTrans = fragMan.beginTransaction();
            fragTrans.setCustomAnimations(R.anim.fade_in,R.anim.fade_out);
            fragTrans.replace(R.id.mainContent, new MenuFragment());
            fragTrans.addToBackStack(null);
            fragTrans.commit();
        }
    };

    /**
     * On Click Listener for the Menu Button
     * Switches to the MenuFragment
     */
    public View.OnClickListener contactButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // Create the instantiate the fragment manager
            fragMan = getActivity().getSupportFragmentManager();
            // Create the fragment transaction
            FragmentTransaction fragTrans = fragMan.beginTransaction();
            fragTrans.setCustomAnimations(R.anim.fade_in,R.anim.fade_out);
            fragTrans.replace(R.id.mainContent, new ContactFragment());
            fragTrans.addToBackStack(null);
            fragTrans.commit();
        }
    };

    /**
     * On Click Listener for the Table Reserve Button
     * Switches to the TableReserveFragment
     */
    public View.OnClickListener reserveTableButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // Create the instantiate the fragment manager
            fragMan = getActivity().getSupportFragmentManager();
            // Create the fragment transaction
            FragmentTransaction fragTrans = fragMan.beginTransaction();
            fragTrans.setCustomAnimations(R.anim.fade_in,R.anim.fade_out);
            fragTrans.replace(R.id.mainContent, new TableReserveFragment());
            fragTrans.addToBackStack(null);
            fragTrans.commit();
        }
    };

    /**
     * On Click Listener for the Bill Estimator Button
     * Switches to the BillEstimatorFragment
     */
    public View.OnClickListener billEstimatorButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // Create the instantiate the fragment manager
            fragMan = getActivity().getSupportFragmentManager();
            // Create the fragment transaction
            FragmentTransaction fragTrans = fragMan.beginTransaction();
            fragTrans.setCustomAnimations(R.anim.fade_in,R.anim.fade_out);
            fragTrans.replace(R.id.mainContent, new BillEstimatorFragment());
            fragTrans.addToBackStack(null);
            fragTrans.commit();
        }
    };

    /**
     * On Click Listener for the Build A Frank Button
     * Switches to the BuildAFrankFragment
     */
    public View.OnClickListener buildaFrankButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // Create the instantiate the fragment manager
            fragMan = getActivity().getSupportFragmentManager();
            // Create the fragment transaction
            FragmentTransaction fragTrans = fragMan.beginTransaction();
            fragTrans.setCustomAnimations(R.anim.fade_in,R.anim.fade_out);
            fragTrans.replace(R.id.mainContent, new BuildAFrankFragment());
            fragTrans.addToBackStack(null);
            fragTrans.commit();
        }
    };

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
