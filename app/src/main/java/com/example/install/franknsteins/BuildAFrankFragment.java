package com.example.install.franknsteins;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.RadioButton;
import android.widget.TextView;


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

        ExpandableListView listView = (ExpandableListView)view.findViewById(R.id.build_menu);
        listView.setAdapter(new CustomListAdapter());

        return view;
    }

    public interface OnFragmentInteractionListener {
    }

    public class CustomListAdapter extends BaseExpandableListAdapter{
        private String[] groups = {"Meats", "Toppings", "Condiments"};

        private String[][] children = {{"Beef", "Pork", "Chicken"},
                                            {"Lettuce", "Onions", "Tomatoes", "Bacon", "Ham",
                                                "Mozzerella", "Swiss", "Cheddar", "Chili", "Egg"},
                                            {"Mustard", "Ketchup", "Mayonnaise", "Relish",
                                                "Ranch", "Gravy"}};

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
            if(convertView == null){
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.build_a_frank_list_item, parent, false);
            }
            TextView textView = (TextView)convertView.findViewById(R.id.main_list_header);
            textView.setText(getGroup(i).toString());
            return textView;
        }

        @Override
        public View getChildView(int i, int j, boolean isLastChild, View convertView, ViewGroup parent) {
            if(i == 0){
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.sub_menu_meat_item, parent, false);
                RadioButton meatButton = (RadioButton)convertView.findViewById(R.id.meat_button);
                meatButton.setText(getChild(i, j).toString());
                return meatButton;
            }
            else{
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.sub_menu_toppings_item, parent, false);
                CheckBox checkBox = (CheckBox)convertView.findViewById(R.id.topping_box);
                checkBox.setText(getChild(i, j).toString());
                return checkBox;
            }
        }

        @Override
        public boolean isChildSelectable(int i, int j) {
            return false;
        }


    }


}
