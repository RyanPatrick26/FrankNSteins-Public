package com.example.install.franknsteins;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
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
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BillEstimatorFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BillEstimatorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BillEstimatorFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.CANADA);
    private static final NumberFormat percentFormat = NumberFormat.getPercentInstance();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ArrayList<Double> priceList;

    protected static double billAmount = 0.0;
    protected static double percent = 0.15;
    protected static double totalAmount;
    private static TextView tipTextView;
    private static TextView totalTextView;
    private static TextView beforeTipTextView;
    private static SeekBar seekBar;
    private static TextView tipLabel;

    private static BillItemAdapter adapter;

    private ListView list;

    private Button clearButton;
    private Button menuButton;

    private OnFragmentInteractionListener mListener;

    public BillEstimatorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BillEstimatorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BillEstimatorFragment newInstance(String param1, String param2) {
        BillEstimatorFragment fragment = new BillEstimatorFragment();
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
        View view = inflater.inflate(R.layout.fragment_bill_estimator, container, false);

        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);

        priceList = MenuFragment.priceList;

        tipTextView = (TextView)view.findViewById(R.id.tip_amount_text_view);
        beforeTipTextView = (TextView)view.findViewById(R.id.before_tip_text_view);
        totalTextView = (TextView)view.findViewById(R.id.total_amount_text_view);
        tipLabel = (TextView)view.findViewById(R.id.tip_percentage);

        seekBar = (SeekBar)view.findViewById(R.id.percent_seek_bar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                // update percent, then call calculate
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress,
                boolean fromUser) {
                    percent = progress / 100.0; // set percent based on progress
                    calculate(); // calculate and display tip and total
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) { }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) { }
        });

        setPrices(priceList);


        menuButton = (Button)view.findViewById(R.id.menu_button);
        clearButton = (Button)view.findViewById(R.id.clear_button);

        list = (ListView)view.findViewById(R.id.bill_list);

        adapter = new BillItemAdapter(getActivity(), MenuFragment.itemList, MenuFragment.priceList);
        list.setAdapter(adapter);

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MenuFragment.itemList.clear();
                MenuFragment.priceList.clear();

                billAmount = 0.0;
                percent = 0.15;
                totalAmount = 0.0;

                list.setAdapter(adapter);

                tipLabel.setText(percentFormat.format(percent));

                seekBar.setProgress(15);

                beforeTipTextView.setText(currencyFormat.format(billAmount));
                tipTextView.setText(currencyFormat.format(billAmount*(percent)));
                totalTextView.setText(currencyFormat.format(totalAmount));
            }
        });
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction trans = fm.beginTransaction();
                trans.setCustomAnimations(R.anim.fade_in,R.anim.fade_out);
                trans.replace(R.id.mainContent, new MenuFragment());
                trans.addToBackStack(null);
                trans.commit();
            }
        });

        return view;
    }

    private void calculate() {
        // format percent and display in percentTextView
        tipLabel.setText(percentFormat.format(percent));

        // calculate the tip and total
        double tip = billAmount * percent;
        double total = billAmount + tip;

        // display tip and total formatted as currency
        tipTextView.setText(currencyFormat.format(tip));
        totalTextView.setText(currencyFormat.format(total));
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

    public static void setPrices(ArrayList<Double> priceList){
        billAmount = 0.0;
        percent = 0.15;
        totalAmount = 0.0;

        for(int i = 0; i < priceList.size(); i++){
            billAmount += priceList.get(i);
        }

        billAmount *= 1.13;
        totalAmount = billAmount+billAmount*percent;

        beforeTipTextView.setText(currencyFormat.format(billAmount));
        tipTextView.setText(currencyFormat.format(billAmount*(percent)));
        totalTextView.setText(currencyFormat.format(totalAmount));

    }
}
