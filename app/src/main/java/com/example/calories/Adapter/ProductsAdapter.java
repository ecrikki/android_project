package com.example.calories.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.calories.Class.Class_prod;
import com.example.calories.Class.Prod;
import com.example.calories.R;

import java.util.ArrayList;

public class ProductsAdapter extends BaseExpandableListAdapter{

    private Context context;
    private ArrayList<Class_prod> class_prodsList;
    private ArrayList<Class_prod> originalList;

    public ProductsAdapter(Context context, ArrayList<Class_prod> class_prodsList){
        this.context = context;
        this.class_prodsList = new ArrayList<>();
        this.class_prodsList.addAll(class_prodsList);
        this.originalList = new ArrayList<>();
        this.originalList.addAll(class_prodsList);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        ArrayList<Prod> prodList = class_prodsList.get(groupPosition).getClass_prodList();
        return prodList.get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        Prod product = (Prod) getChild(groupPosition, childPosition);
        if(convertView == null)
        {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.child_product, null);
        }
        TextView name = convertView.findViewById(R.id.ID);
        TextView population = convertView.findViewById(R.id.kkal);
        name.setText(product.getId_product().trim());
        population.setText(product.getКалорийность().trim()  + " " + "ккал/100г");
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        ArrayList<Prod> prodList = class_prodsList.get(groupPosition).getClass_prodList();
        return prodList.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return class_prodsList.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return class_prodsList.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        Class_prod class_prod = (Class_prod) getGroup(groupPosition);
        if(convertView == null)
        {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.type_of_product, null);
        }
        TextView heading = convertView.findViewById(R.id.heading);
        heading.setText(class_prod.getName().trim());
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public void filterData(String query)
    {
        query = query.toLowerCase();
        Log.v("ProductsAdapter", String.valueOf(class_prodsList.size()));
        class_prodsList.clear();

        if(query.isEmpty())
        {
            class_prodsList.addAll(originalList);
        }
        else
        {
            for(Class_prod class_prod: originalList)
            {
                ArrayList<Prod> prodList = class_prod.getClass_prodList();
                ArrayList<Prod> newList = new ArrayList<Prod>();
                for(Prod prod: prodList)
                {
                    if(prod.getId_product().toLowerCase().contains(query))
                    {
                        newList.add(prod);
                    }
                }
                if(newList.size() > 0){
                    Class_prod nClass_prod = new Class_prod(class_prod.getName(), newList);
                    class_prodsList.add(nClass_prod);
                }
            }
        }
        Log.v("ProductsAdapter", String.valueOf(class_prodsList.size()));
        notifyDataSetChanged();
    }
}

