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
    private final int imgId;

    public MenuItemAdapter(Activity context, String[] itemName, String[] itemDescription, String[] itemPrice, int imgId){
        super(context, R.layout.menu_item, itemName);
        this.context = context;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemPrice = itemPrice;
        this.imgId = imgId;
    }

    public MenuItemAdapter(Activity context){
        super(context, R.layout.menu_item);
        this.context = context;
        this.itemName = new String[]{""};
        this.itemDescription = new String[]{""};
        this.itemPrice = new String[]{""};
        this.imgId = 0;

    }

    public View getView(int position, View view, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View menuView = inflater.inflate(R.layout.menu_item, null, true);
        TextView itemTextView = (TextView)menuView.findViewById(R.id.menu_selection);
        TextView itemDescriptionTextView = (TextView)menuView.findViewById(R.id.description_text);
        TextView itemPriceTextView = (TextView) menuView.findViewById(R.id.price);
        ImageView itemImage = (ImageView)menuView.findViewById(R.id.item_image);

        itemTextView.setText(itemName[position]);
        itemDescriptionTextView.setText(itemDescription[position]);
        itemPriceTextView.setText(itemPrice[position]);
        itemImage.setImageResource(imgId);

        TextView addButton = (TextView)menuView.findViewById(R.id.add_button);

        return menuView;
    }
}
