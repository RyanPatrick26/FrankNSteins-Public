package com.example.install.franknsteins;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MenuListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MenuListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MenuListFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private int position;

    private String[] frankItemNames;
    private String[] frankItemDescriptions;
    private String[] frankItemPrices;

    private String[] steinItemNames;
    private String[] steinItemDescriptions;
    private String[] steinItemPrices;

    private String[] nonAlcItemNames;
    private String[] nonAlcItemDescriptions;
    private String[] nonAlcItemPrices;

    private OnFragmentInteractionListener mListener;

    public MenuListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment MenuListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MenuListFragment newInstance(String param1, int position) {
        MenuListFragment fragment = new MenuListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putInt(ARG_PARAM2, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            position = getArguments().getInt(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu_list, container, false);

        ListView list = (ListView)view.findViewById(R.id.menu_content);

        frankItemNames = getResources().getStringArray(R.array.frank_item_names);
        frankItemDescriptions  =getResources().getStringArray(R.array.frank_item_descriptions);
        frankItemPrices = getResources().getStringArray(R.array.frank_item_prices);

        steinItemNames = getResources().getStringArray(R.array.stein_item_names);
        steinItemDescriptions = getResources().getStringArray(R.array.stein_item_descriptions);
        steinItemPrices = getResources().getStringArray(R.array.stein_item_prices);

        nonAlcItemNames = getResources().getStringArray(R.array.non_alc_item_names);
        nonAlcItemDescriptions = getResources().getStringArray(R.array.non_alc_item_descriptions);
        nonAlcItemPrices = getResources().getStringArray(R.array.non_alc_item_prices);

        if(mParam1 != null){
            TextView titleTextView = (TextView)view.findViewById(R.id.title_text_view);
            titleTextView.setText(mParam1);
            MenuItemAdapter adapter;
            if(position == 0){
                adapter = new MenuItemAdapter(getActivity(), frankItemNames, frankItemDescriptions, frankItemPrices);
            }
            else if(position == 1){
                adapter = new MenuItemAdapter(getActivity(), steinItemNames, steinItemDescriptions, steinItemPrices);
            }
            else{
                adapter = new MenuItemAdapter(getActivity(), nonAlcItemNames, nonAlcItemDescriptions, nonAlcItemPrices);
            }
            list.setAdapter(adapter);
        }
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
     * <p>
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
