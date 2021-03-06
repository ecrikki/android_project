package com.example.calories.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.calories.Class.Class_prod;
import com.example.calories.Class.Prod;
import com.example.calories.R;

import java.util.ArrayList;

import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;

public class ProductsAdapter_main extends BaseExpandableListAdapter{

    private Context context;
    private ArrayList<Class_prod> class_prodsList;
    private ArrayList<Class_prod> originalList;

    public ProductsAdapter_main(Context context, ArrayList<Class_prod> class_prodsList){
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

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.dish, null);
        }

        TextView name = convertView.findViewById(R.id.ID);
        TextView kkal = convertView.findViewById(R.id.kkal);
        TextView gramm = convertView.findViewById(R.id.gramm);
        name.setText(product.getId_product().trim());
        int kkal_gr = (int)Math.round(parseFloat(product.getКалорийность()) / (float)100 * parseFloat(product.getGramm()));
        kkal.setText(Integer.toString(kkal_gr).trim() + " " + "ккал");
        gramm.setText(product.getGramm().trim() + " " + "г");
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

    @SuppressLint("ResourceType")
    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        Class_prod class_prod = (Class_prod) getGroup(groupPosition);
        if(convertView == null)
        {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.eating, null);
        }
        TextView heading = convertView.findViewById(R.id.heading);
        heading.setText(class_prod.getName().trim());
        ImageView img = convertView.findViewById(R.id.imageView2);

        String name = ((Class_prod) getGroup(groupPosition)).getName();

        if(img != null)
        {
            switch (name) {
                case "Завтрак":
                    img.setId(0);
                    break;
                case "Обед":
                    img.setId(1);
                    break;
                case "Ужин":
                    img.setId(2);
                    break;
                case "Другое":
                    img.setId(3);
                    break;
                default:
                    break;
            }
        }
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
}
