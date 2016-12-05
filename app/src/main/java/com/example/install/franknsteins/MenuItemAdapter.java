package com.example.install.franknsteins;

import android.app.Activity;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Ryan Patrick on 11/28/2016.
 */
public class MenuItemAdapter extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] itemName;
    private final String[] itemDescription;
    private final String[] itemPrice;
    private ArrayList<String> itemList;
    private ArrayList<Double> priceList;

    public MenuItemAdapter(Activity context, String[] itemName, String[] itemDescription, String[] itemPrice){
        super(context, R.layout.menu_item, itemName);
        this.context = context;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemPrice = itemPrice;
        itemList = new ArrayList<>();
        priceList = new ArrayList<>();
    }

    public View getView(int position, View view, final ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View menuView = inflater.inflate(R.layout.menu_item, null, true);
        final TextView itemTextView = (TextView)menuView.findViewById(R.id.menu_selection);
        itemTextView.setText(itemName[position]);

        if(itemDescription != null){
            TextView itemDescriptionTextView = (TextView)menuView.findViewById(R.id.description_text);
            itemDescriptionTextView.setText(itemDescription[position]);
        }

        final TextView itemPriceTextView = (TextView) menuView.findViewById(R.id.price);
        itemPriceTextView.setText("$" + itemPrice[position]);

        TextView addButton = (TextView)menuView.findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemList.add((String) itemTextView.getText());
                String itemPriceString = (String)itemPriceTextView.getText().subSequence(1, 5);
                priceList.add(Double.parseDouble(itemPriceString));

                MenuFragment.itemList.addAll(itemList);
                MenuFragment.priceList.addAll(priceList);

                Snackbar snackbar = Snackbar.make(parent, "Added to your order", Snackbar.LENGTH_SHORT);
                snackbar.show();
            }
        });

        return menuView;
    }
}
