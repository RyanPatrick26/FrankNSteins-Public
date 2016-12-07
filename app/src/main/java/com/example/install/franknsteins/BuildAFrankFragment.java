package com.example.install.franknsteins;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ExpandableListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BuildAFrankFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BuildAFrankFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BuildAFrankFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    TextView meatTextView;
    TextView toppingTextView;

    private OnFragmentInteractionListener mListener;

    public BuildAFrankFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BuildAFrankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BuildAFrankFragment newInstance(String param1, String param2) {
        BuildAFrankFragment fragment = new BuildAFrankFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_build_afrank, container, false);
        meatTextView = (TextView)view.findViewById(R.id.meat_text_view);
        meatTextView.setText(null);
        toppingTextView = (TextView)view.findViewById(R.id.topping_text_view);
        //create the expandable list view and assign it a custom built adapter
        ExpandableListView listView = (ExpandableListView)view.findViewById(R.id.build_menu);
        listView.setAdapter(new CustomListAdapter());

        Button submitButton = (Button)view.findViewById(R.id.submit_creation_button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar snackbar = Snackbar.make(getActivity().findViewById(android.R.id.content),
                        "Your creation has been saved!", Snackbar.LENGTH_SHORT);
                snackbar.show();
            }
        });

        return view;
    }

    public interface OnFragmentInteractionListener {
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

    public class CustomListAdapter extends BaseExpandableListAdapter{
        //create an array containing a list of the headings for the main list
        private String[] groups = {"Meats", "Toppings", "Condiments"};

        //create a 2 dimensional array containing all the items to go into the sub lists
        private String[][] children = {{""},
                                        {getString(R.string.buildFrank_lettuce), getString(R.string.buildFrank_onions), getString(R.string.buildFrank_tomato),
                                                getString(R.string.buildFrank_bacon), getString(R.string.buildFrank_ham), getString(R.string.buildFrank_moz),
                                                getString(R.string.buildFrank_swiss), getString(R.string.buildFrank_cheddar), getString(R.string.buildFrank_chili),
                                                getString(R.string.buildFrank_egg)},
                                        {getString(R.string.buildFrank_mustard), getString(R.string.buildFrank_ketchup),getString(R.string.buildFrank_mayo),
                                                getString(R.string.buildFrank_relish), getString(R.string.buildFrank_ranch), getString(R.string.buildFrank_gravy)}};
       private ArrayList<String> toppingList = new ArrayList<>();

        //required methods from the BaseExpandableListAdapter class
        public int getGroupCount(){
            return groups.length;
        }

        public int getChildrenCount(int i){
            return children[i].length;
        }

        public Object getGroup(int i){
            return groups[i];
        }

        public Object getChild(int i, int j){
            return children[i][j];
        }

        public long getGroupId(int i){
            return i;
        }

        public long getChildId(int i, int j){
            return j;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public View getGroupView(int i, boolean isExpanded, View convertView, ViewGroup parent) {
            //initialize the convertView to the layout for the main list headers
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.build_a_frank_list_item, parent, false);
            //create a TextView for the main headers and put the String value for that header
            //using the groups array
            TextView textView = (TextView)convertView.findViewById(R.id.main_list_header);
            textView.setText(getGroup(i).toString());
            return textView;
        }
        @Override
        public View getChildView(int i, int j, boolean isLastChild, View convertView, ViewGroup parent) {
            if(i == 0){
                int states[][] = {{android.R.attr.state_checked},{}};
                int colors[] = {R.color.frankTextColor, R.color.hotdog_color};
                //initialize the convertView to the layout for the sub-list that uses radio buttons
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.meat_sub_menu, parent, false);
                //create a RadioGroup that holds the different radio buttons for the different meats
                RadioGroup group = (RadioGroup)convertView.findViewById(R.id.meat_group);

                //create variables for the different radio buttons
                final RadioButton porkButton = (RadioButton)convertView.findViewById(R.id.pork_radio_button);
                final RadioButton beefButton = (RadioButton)convertView.findViewById(R.id.beef_radio_button);
                final RadioButton chickenButton = (RadioButton) convertView.findViewById(R.id.chicken_radio_button);
                porkButton.setButtonTintList(new ColorStateList(states, colors));
                beefButton.setButtonTintList(new ColorStateList(states, colors));
                chickenButton.setButtonTintList(new ColorStateList(states, colors));


                //Create event handler for selecting and deselecting the different radio buttons
                //Adds the String Value of the Checked Radio Button to the Meat Text View
                group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        if(checkedId == porkButton.getId()){
                            meatTextView.setText("");
                            meatTextView.append("* " + porkButton.getText());
                        }
                        else if(checkedId == beefButton.getId()){
                            meatTextView.setText("");
                            meatTextView.append("* " + beefButton.getText());
                        }
                        else{
                            meatTextView.setText("");
                            meatTextView.append("* " + chickenButton.getText());
                        }
                    }
                });

                return group;
            }
            else{
                int states[][] = {{android.R.attr.state_checked},{}};
                int colors[] = {R.color.frankTextColor, R.color.hotdog_color};
                //initialize the convertView to the layout for the sub-list that uses check boxes
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.sub_menu_toppings_item, parent, false);
                //create a CheckBox for the topping and condiments sub-headers and put the String value for those
                //list items using the children array
                final CheckBox checkBox = (CheckBox)convertView.findViewById(R.id.topping_box);
                checkBox.setText(getChild(i, j).toString());
                checkBox.setButtonTintList(new ColorStateList(states, colors));

                //create event handler for selecting and deselecting the different checkboxes
                //adds the string value of the checkbox to the Toppings Text View
                checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if(isChecked){
                            toppingList.add(checkBox.getText().toString());
                        }
                        else{
                            toppingList.remove(checkBox.getText().toString());
                        }
                        toppingTextView.setText("");
                        for(int i = 0; i < toppingList.size(); i++){
                            toppingTextView.append("* " + toppingList.get(i) + "\n");
                        }
                    }
                });

                return checkBox;
            }
        }

        @Override
        public boolean isChildSelectable(int i, int j) {
            return false;
        }

    }

}
