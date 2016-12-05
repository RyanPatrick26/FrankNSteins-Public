package com.example.install.franknsteins;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Ryan Patrick on 12/5/2016.
 */
public class BillItemAdapter extends ArrayAdapter<String>{
    private final Activity context;
    private ArrayList<String> itemList;
    private ArrayList<Double> priceList;

    public BillItemAdapter(Activity context, ArrayList<String> itemList, ArrayList<Double> priceList) {
        super(context, R.layout.estimator_menu_item, itemList);
        this.context = context;
        this.itemList = itemList;
        this.priceList = priceList;
    }

    public View getView(final int position, final View view, final ViewGroup parent){
        LayoutInflater layoutInflater = context.getLayoutInflater();
        View estimatorView = layoutInflater.inflate(R.layout.estimator_menu_item, null, true);

        TextView itemTextView = (TextView)estimatorView.findViewById(R.id.item_text_view);
        TextView priceTextView = (TextView)estimatorView.findViewById(R.id.price_text_view);
        TextView removeButton = (TextView)estimatorView.findViewById(R.id.remove_button);

        itemTextView.setText(itemList.get(position));
        priceTextView.setText("$" + priceList.get(position));

        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MenuFragment.itemList.remove(MenuFragment.itemList.get(position));
                MenuFragment.priceList.remove(MenuFragment.priceList.get(position));

                itemList = MenuFragment.itemList;
                priceList = MenuFragment.priceList;

                BillEstimatorFragment.setPrices(priceList);
            }
        });



        return estimatorView;
    }
}
