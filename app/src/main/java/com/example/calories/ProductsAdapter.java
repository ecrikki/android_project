package com.example.calories;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.ArrayList;
import java.util.List;

public class ProductsAdapter extends ExpandableRecyclerViewAdapter<Class_prodViewHolder, ProdViewHolder> {
    public ProductsAdapter(List<? extends ExpandableGroup> groups) {
        super(groups);
    }

    @Override
    public Class_prodViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.class_of_product, parent, false);
        return new Class_prodViewHolder(v);
    }

    @Override
    public ProdViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.product, parent, false);
        return new ProdViewHolder(v);
    }

    @Override
    public void onBindChildViewHolder(ProdViewHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {
        final Prod product = (Prod) group.getItems().get(childIndex);
        holder.bind(product);
    }

    @Override
    public void onBindGroupViewHolder(Class_prodViewHolder holder, int flatPosition, ExpandableGroup group) {
        final Class_prod company = (Class_prod) group;
        holder.bind(company);
    }
}

