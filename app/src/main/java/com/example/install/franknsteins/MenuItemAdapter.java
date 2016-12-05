package com.example.install.franknsteins;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Ryan Patrick on 11/28/2016.
 */
public class MenuItemAdapter extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] itemName;
    private final String[] itemDescription;
    private final String[] itemPrice;

    public MenuItemAdapter(Activity context, String[] itemName, String[] itemDescription, String[] itemPrice){
        super(context, R.layout.menu_item, itemName);
        this.context = context;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemPrice = itemPrice;
    }

    public View getView(int position, View view, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View menuView = inflater.inflate(R.layout.menu_item, null, true);
        TextView itemTextView = (TextView)menuView.findViewById(R.id.menu_selection);
        itemTextView.setText(itemName[position]);

        if(itemDescription != null){
            TextView itemDescriptionTextView = (TextView)menuView.findViewById(R.id.description_text);
            itemDescriptionTextView.setText(itemDescription[position]);
        }

        TextView itemPriceTextView = (TextView) menuView.findViewById(R.id.price);
        itemPriceTextView.setText("$" + itemPrice[position]);

        TextView addButton = (TextView)menuView.findViewById(R.id.add_button);

        return menuView;
    }
}
