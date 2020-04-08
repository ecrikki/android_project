package com.example.calories.ViewHolder_Adapter;

import android.view.View;
import android.widget.TextView;

import com.example.calories.Class.Prod;
import com.example.calories.R;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

public class ProdViewHolder extends ChildViewHolder {
    private TextView id, kkal;

    public ProdViewHolder(View itemView) {
        super(itemView);
        id = itemView.findViewById(R.id.ID);
        kkal = itemView.findViewById(R.id.kkal);
    }

    public void bind(Prod prod){

        id.setText(prod.id_product);
        kkal.setText(prod.калорийность + " " + "ккал/100г");
    }
}
