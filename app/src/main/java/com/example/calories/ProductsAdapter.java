package com.example.calories;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder> {
    ArrayList <Products> list;
    String inf = "ккал/100г";

    public ProductsAdapter(ArrayList <Products> list){
        this.list = list;
    }

    @Override
    public ProductsViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        return new ProductsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_holder, parent, false));
    }

    @Override
    public void onBindViewHolder(ProductsViewHolder holder, int position) {
        holder.ID.setText(list.get(position).getId_product());
        holder.Kkal.setText(list.get(position).getКалорийность() + " " + inf);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ProductsViewHolder extends RecyclerView.ViewHolder{
        TextView ID, Kkal;

        public ProductsViewHolder(View itemView){
            super(itemView);

            ID = itemView.findViewById(R.id.ID);
            Kkal = itemView.findViewById(R.id.kkal);
        }
    }
}

