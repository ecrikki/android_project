package com.example.calories;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.calories.Adapter.ProductsAdapter;
import com.example.calories.Adapter.ProductsAdapter_main;
import com.example.calories.Class.Class_prod;
import com.example.calories.Class.Prod;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity
{
    private TextView text_date;
    private ProductsAdapter_main adapter;
    private ExpandableListView myList;
    private ArrayList<Class_prod> class_prodList = new ArrayList<>();
    ArrayList<Prod> product0 = new ArrayList<>();
    ArrayList<Prod> product1 = new ArrayList<>();
    ArrayList<Prod> product2 = new ArrayList<>();
    ArrayList<Prod> product3 = new ArrayList<>();

    private Button b1, b2, b3, b4;

    private int actText, actBk;
    private  int naText , naBk;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text_date = findViewById(R.id.textView6);
        Date currentDate = new Date(); // Текущее время
        // Форматирование времени как "день недели.день.месяц"
        DateFormat dateFormat = new SimpleDateFormat("EEEE dd MMMM", Locale.getDefault());
        String dateText = dateFormat.format(currentDate);


        myList = findViewById(R.id.expandableList);

        Class_prod class_prod = new Class_prod("Завтрак", product0);
        class_prodList.add(class_prod);
        adapter = new ProductsAdapter_main(this, class_prodList);
        myList.setAdapter(adapter);

        class_prod = new Class_prod("Обед", product1);
        class_prodList.add(class_prod);
        adapter = new ProductsAdapter_main(this, class_prodList);
        myList.setAdapter(adapter);

        class_prod = new Class_prod("Ужин", product2);
        class_prodList.add(class_prod);
        adapter = new ProductsAdapter_main(this, class_prodList);
        myList.setAdapter(adapter);

        class_prod = new Class_prod("Другое", product3);
        class_prodList.add(class_prod);
        adapter = new ProductsAdapter_main(this, class_prodList);
        myList.setAdapter(adapter);

        expandAll();

        text_date.setText(dateText);
        b1 = findViewById(R.id.button_today);
        b2 = findViewById(R.id.button_history);
        b3 = findViewById(R.id.button_parameters);
        b4 = findViewById(R.id.button_settings);

        actText =  getResources().getColor(R.color.Bard);
        actBk   = getResources().getColor(R.color.Peach);
        naText = getResources().getColor(R.color.Peach);
        naBk = getResources().getColor(R.color.Bard);

        b1.setBackgroundColor(actBk);
        b1.setTextColor(actText);

        b2.setBackgroundResource(R.color.Bard);
        b3.setBackgroundResource(R.color.Bard);
        b4.setBackgroundResource(R.color.Bard);
    }

    public void onClick_Today(View view) {
        b3.setBackgroundColor(naBk);
        b3.setTextColor(naText);
        b4.setBackgroundColor(naBk);
        b4.setTextColor(naText);

        b1.setBackgroundColor(actBk);
        b1.setTextColor(actText);
        b2.setBackgroundColor(naBk);
        b2.setTextColor(naText);
        b3.setBackgroundResource(R.color.Bard);
        b4.setBackgroundResource(R.color.Bard);
    }

    public void onClick_History(View view) {
        b2.setBackgroundColor(actBk);
        b2.setTextColor(actText);

        b1.setBackgroundColor(naBk);
        b1.setTextColor(naText);
        b3.setBackgroundColor(naBk);
        b3.setTextColor(naText);
        b4.setBackgroundColor(naBk);
        b4.setTextColor(naText);
    }

    public void onClick_Parameters(View view) {
        b3.setBackgroundColor(actBk);
        b3.setTextColor(actText);

        b1.setBackgroundColor(naBk);
        b1.setTextColor(naText);
        b2.setBackgroundColor(naBk);
        b2.setTextColor(naText);
        b4.setBackgroundColor(naBk);
        b4.setTextColor(naText);
    }

    public void onClick_Settings(View view) {
        b4.setBackgroundColor(actBk);
        b4.setTextColor(actText);
        b1.setBackgroundColor(naBk);
        b1.setTextColor(naText);
        b2.setBackgroundColor(naBk);
        b2.setTextColor(naText);
        b3.setBackgroundColor(naBk);
        b3.setTextColor(naText);

    }

    public void onClick_Add(View view) {
        Intent i = new Intent(MainActivity.this, Add_Activity.class);
        startActivityForResult(i, view.getId());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        Prod prod = (Prod) data.getSerializableExtra("prod");

        if(requestCode == 0){
            product0.add(prod);
            Class_prod class_prod = new Class_prod("Завтрак", product0);
            class_prodList.add(class_prod);
            adapter.notifyDataSetChanged();
        }
        else if(requestCode == 1){
            product1.add(prod);
            Class_prod class_prod = new Class_prod("Обед", product1);
            class_prodList.add(class_prod);
            adapter.notifyDataSetChanged();
        }
        else if(requestCode == 2){
            product2.add(prod);
            Class_prod class_prod = new Class_prod("Ужин", product2);
            class_prodList.add(class_prod);
            adapter.notifyDataSetChanged();
        }
        else{
            product3.add(prod);
            Class_prod class_prod = new Class_prod("Другое", product3);
            class_prodList.add(class_prod);
            adapter.notifyDataSetChanged();
        }
        expandAll();
    }

    private void expandAll(){
        int count = adapter.getGroupCount();
        for(int i = 0; i < count; i++)
        {
            myList.expandGroup(i);
        }
    }
}
