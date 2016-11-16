package com.example.install.franknsteins;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

/**
 * Created by Ryan Patrick on 11/16/2016.
 */
public class CustomAdapter extends BaseExpandableListAdapter {

    private String[] categories = {"Meats", "Toppings", "Comdiments"};
    private String[][] children = {{"Chicken", "Pork", "Beef"},{""},{""}};

    @Override
    public int getGroupCount() {
        return categories.length;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return children[groupPosition].length;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return categories[groupPosition];
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return children[groupPosition][childPosition];
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        TextView textView = new TextView(BuildAFrankFragment.this.getActivity());
        textView.setText(getGroup(groupPosition).toString());
        return textView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        TextView textView = new TextView(BuildAFrankFragment.this.getActivity());
        textView.setText(getChild(groupPosition, childPosition).toString());
        return textView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
