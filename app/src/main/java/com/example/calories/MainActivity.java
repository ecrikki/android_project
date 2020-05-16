package com.example.calories;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.calories.Adapter.ProductsAdapter_main;
import com.example.calories.Class.Class_prod;
import com.example.calories.Class.Prod;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import static java.lang.Float.parseFloat;

public class MainActivity extends AppCompatActivity
{
    private int kkal_gr = 0;
    private TextView sum_kkal;
    private ProductsAdapter_main adapter;
    private ExpandableListView myList;
    private ArrayList<Class_prod> class_prodList = new ArrayList<>();
    ArrayList<Prod> product0 = new ArrayList<>();
    ArrayList<Prod> product1 = new ArrayList<>();
    ArrayList<Prod> product2 = new ArrayList<>();
    ArrayList<Prod> product3 = new ArrayList<>();
    SharedPreferences sPref;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView text_date = findViewById(R.id.textView6);
        sum_kkal = findViewById(R.id.sum_kkal);
        Date currentDate = new Date(); // Текущее время
        // Форматирование времени как "день недели.день.месяц"
        DateFormat dateFormat = new SimpleDateFormat("EEEE dd MMMM", Locale.getDefault());
        String dateText = dateFormat.format(currentDate);
        text_date.setText(dateText);

        String date = Get_date();
        if (date == null){
            Save_date(dateText);
        }
        else if (!date.equals(dateText)){
            Clear();
            Save_date(dateText);
        }

        myList = findViewById(R.id.expandableList);

        Class_prod prod = Get("Завтрак");
        String gramm = "";
        if (prod != null) {
            product0 = prod.getClass_prodList();
            class_prodList.add(prod);
            adapter = new ProductsAdapter_main(this, class_prodList);
            myList.setAdapter(adapter);
        }
        else{
            Class_prod class_prod = new Class_prod("Завтрак", gramm, product0);
            class_prodList.add(class_prod);
            adapter = new ProductsAdapter_main(this, class_prodList);
            myList.setAdapter(adapter);
        }

        prod = Get("Обед");
        if (prod != null) {
            product1 = prod.getClass_prodList();
            class_prodList.add(prod);
            adapter = new ProductsAdapter_main(this, class_prodList);
            myList.setAdapter(adapter);
        }
        else{
            Class_prod class_prod = new Class_prod("Обед", gramm, product1);
            class_prodList.add(class_prod);
            adapter = new ProductsAdapter_main(this, class_prodList);
            myList.setAdapter(adapter);
        }

        prod = Get("Ужин");
        if (prod != null) {
            product2 = prod.getClass_prodList();
            class_prodList.add(prod);
            adapter = new ProductsAdapter_main(this, class_prodList);
            myList.setAdapter(adapter);
        }
        else{
            Class_prod class_prod = new Class_prod("Ужин", gramm, product2);
            class_prodList.add(class_prod);
            adapter = new ProductsAdapter_main(this, class_prodList);
            myList.setAdapter(adapter);
        }

        prod = Get("Другое");
        if (prod != null) {
            product3 = prod.getClass_prodList();
            class_prodList.add(prod);
            adapter = new ProductsAdapter_main(this, class_prodList);
            myList.setAdapter(adapter);
        }
        else{
            Class_prod class_prod = new Class_prod("Другое", gramm, product3);
            class_prodList.add(class_prod);
            adapter = new ProductsAdapter_main(this, class_prodList);
            myList.setAdapter(adapter);
        }
        expandAll();

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setLogo(R.drawable.logo_small);
    }

    public void onClick_Today(View view) {

    }

    public void onClick_History(View view) {

    }

    public void onClick_Parameters(View view) {

    }

    public void onClick_Settings(View view) {

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
        String gramm = data.getStringExtra("gramm");
        prod.setGramm(gramm);

        if(requestCode == 0){
            product0.add(prod);
            Class_prod class_prod = new Class_prod("Завтрак", gramm, product0);
            Save(class_prod, "Завтрак");
            class_prodList.set(0, class_prod);
            adapter.notifyDataSetChanged();
        }
        else if(requestCode == 1){
            product1.add(prod);
            Class_prod class_prod = new Class_prod("Обед", gramm, product1);
            Save(class_prod, "Обед");
            class_prodList.set(1, class_prod);
            adapter.notifyDataSetChanged();
        }
        else if(requestCode == 2){
            product2.add(prod);
            Class_prod class_prod = new Class_prod("Ужин", gramm, product2);
            Save(class_prod, "Ужин");
            class_prodList.set(2, class_prod);
            adapter.notifyDataSetChanged();
        }
        else{
            product3.add(prod);
            Class_prod class_prod = new Class_prod("Другое", gramm, product3);
            Save(class_prod, "Другое");
            class_prodList.set(3, class_prod);
            adapter.notifyDataSetChanged();
        }
        expandAll();

        kkal_gr += Math.round(parseFloat(prod.getКалорийность()) / (float)100 * parseFloat(prod.getGramm()));
        sum_kkal.setText("Всего:" + " " + Integer.toString(kkal_gr).trim() + " " + "ккал");
    }

    private void Clear(){
        sPref = getSharedPreferences("day_eat", MODE_PRIVATE);
        SharedPreferences.Editor editor = sPref.edit();
        editor.clear().apply();
    }

    private void Save_date(String dateText){
        sPref = getSharedPreferences("date", MODE_PRIVATE);
        SharedPreferences.Editor editor = sPref.edit();
        editor.putString("Дата", dateText);
        editor.apply();
    }

    private String Get_date() {
        sPref = getSharedPreferences("date", MODE_PRIVATE);
        return sPref.getString("Дата", "");
    }

    private void Save(Class_prod class_prod, String day){
        sPref = getSharedPreferences("day_eat", MODE_PRIVATE);
        SharedPreferences.Editor editor = sPref.edit();
        Gson gson = new Gson();
        String json = gson.toJson(class_prod);
        editor.putString(day, json);
        editor.apply();
    }

    private Class_prod Get(String day) {
        sPref = getSharedPreferences("day_eat", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sPref.getString(day, "");
        Type type = new TypeToken<Class_prod>() {
        }.getType();
        return gson.fromJson(json, type);
    }

    private void expandAll(){
        int count = adapter.getGroupCount();
        for(int i = 0; i < count; i++)
        {
            myList.expandGroup(i);
        }
    }
}
