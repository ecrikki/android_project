package com.example.calories;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.calories.Adapter.ProductsAdapter_main;
import com.example.calories.Class.Class_prod;
import com.example.calories.Class.Prod;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

        BottomNavigationView menu = findViewById(R.id.menu);
        menu.setSelectedItemId(R.id.today);
        menu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.today){
                    return true;
                }
                else if(item.getItemId() == R.id.history){
                    startActivity(new Intent(getApplicationContext(), History_Activity.class));
                    overridePendingTransition(0, 0);
                    return true;
                }
                else if(item.getItemId() == R.id.parameters){
                    startActivity(new Intent(getApplicationContext(), Parameter_Activity.class));
                    overridePendingTransition(0, 0);
                    return true;
                }
                else if(item.getItemId() == R.id.settings){
                    startActivity(new Intent(getApplicationContext(), Settings_Activity.class));
                    overridePendingTransition(0, 0);
                    return true;
                }
                return false;
            }
        });

        TextView text_date = findViewById(R.id.textView6);
        sum_kkal = findViewById(R.id.sum_kkal);
        Date currentDate = new Date(); // Текущее время
        // Форматирование времени как "день недели.день.месяц"
        DateFormat dateFormat = new SimpleDateFormat("EEEE dd MMMM", Locale.getDefault());
        String dateText = dateFormat.format(currentDate);
        text_date.setText(dateText);

        String date = Get_date();
        if (date == null){
            Save_statistic(kkal_gr);
            Save_date(dateText);
        }
        else if (!date.equals(dateText)){
            Save_statistic(kkal_gr);
            Clear();
            Save_date(dateText);
        }

        myList = findViewById(R.id.expandableList);

        int sum = Get_sum();
        if (sum != 0) {
            kkal_gr = sum;
            sum_kkal.setText("Всего:" + " " + sum + " " + "ккал");
        }

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
        Delete_prod();

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setLogo(R.drawable.logo_small);
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
        sum_kkal.setText("Всего:" + " " + kkal_gr + " " + "ккал");
        Save_sum(kkal_gr);
        Save_statistic(kkal_gr);
    }

    private void Get_statistic() {
        sPref = getSharedPreferences("statistic", MODE_PRIVATE);
        SharedPreferences.Editor editor = sPref.edit();
        DateFormat dateFormat = new SimpleDateFormat("dd MMMM", Locale.getDefault());
        int j = 0;
        int start = 54;
        for (int i = 13; i >= 0; i--) {
            Calendar c = Calendar.getInstance();
            c.add(Calendar.DAY_OF_MONTH, -i);
            String first_date = dateFormat.format(c.getTime());
            editor.putInt(first_date, start);
            editor.apply();
            start = start + 2;
        }
    }

    private void Clear(){
        sPref = getSharedPreferences("statistic", MODE_PRIVATE);
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

    private void Save_statistic(int kkal){
        sPref = getSharedPreferences("statistic", MODE_PRIVATE);
        SharedPreferences.Editor editor = sPref.edit();

        DateFormat dateFormat = new SimpleDateFormat("dd MMMM", Locale.getDefault());
        Calendar c = Calendar.getInstance();
        String date_statistic = dateFormat.format(c.getTime());
        c.add(Calendar.DAY_OF_MONTH, -14);
        String first_date = dateFormat.format(c.getTime());

        if (sPref.getInt(first_date, 0) != 0){
            editor.remove(first_date);
        }
        editor.putInt(date_statistic, kkal);
        editor.apply();
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

    private void Save_sum(Integer sum_k) {
        sPref = getSharedPreferences("day_eat", MODE_PRIVATE);
        SharedPreferences.Editor editor = sPref.edit();
        editor.putInt("сумма", sum_k);
        editor.apply();
    }

    private int Get_sum() {
        sPref = getSharedPreferences("day_eat", MODE_PRIVATE);
        return sPref.getInt("сумма", 0);
    }

    private void Delete_prod() {
        myList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if (ExpandableListView.getPackedPositionType(id) == ExpandableListView.PACKED_POSITION_TYPE_CHILD) {
                    int groupPosition = ExpandableListView.getPackedPositionGroup(id);
                    int childPosition = ExpandableListView.getPackedPositionChild(id);


                    final Prod selected = (Prod) adapter.getChild(groupPosition, childPosition);
                    final Class_prod eat = (Class_prod) adapter.getGroup(groupPosition);

                    LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
                    final View dialogView = inflater.inflate(R.layout.dialog_delet, null);
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setView(dialogView);

                    Button yes = dialogView.findViewById(R.id.yes);
                    Button no = dialogView.findViewById(R.id.no);

                    TextView question = dialogView.findViewById(R.id.text);
                    question.setText("Вы действительно хотите удалить продукт" + " " + "(" + selected.getId_product() + ")?");

                    final AlertDialog alertDialog = builder.create();
                    alertDialog.show();

                    yes.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String gramm = "";
                            if (eat.getName().equals("Завтрак")){
                                product0.remove(selected);
                                Class_prod class_prod = new Class_prod("Завтрак", gramm, product0);
                                Save(class_prod, "Завтрак");
                                class_prodList.set(0, class_prod);
                                adapter.notifyDataSetChanged();
                            }
                            else if (eat.getName().equals("Обед")){
                                product1.remove(selected);
                                Class_prod class_prod = new Class_prod("Обед", gramm, product1);
                                Save(class_prod, "Обед");
                                class_prodList.set(1, class_prod);
                                adapter.notifyDataSetChanged();
                            }
                            else if (eat.getName().equals("Ужин")){
                                product2.remove(selected);
                                Class_prod class_prod = new Class_prod("Ужин", gramm, product2);
                                Save(class_prod, "Ужин");
                                class_prodList.set(2, class_prod);
                                adapter.notifyDataSetChanged();
                            }
                            else {
                                product3.remove(selected);
                                Class_prod class_prod = new Class_prod("Другое", gramm, product3);
                                Save(class_prod, "Другое");
                                class_prodList.set(3, class_prod);
                                adapter.notifyDataSetChanged();
                            }
                            kkal_gr -= Math.round(parseFloat(selected.getКалорийность()) / (float)100 * parseFloat(selected.getGramm()));
                            sum_kkal.setText("Всего:" + " " + kkal_gr + " " + "ккал");
                            Save_sum(kkal_gr);
                            Save_statistic(kkal_gr);
                            alertDialog.dismiss();
                        }
                    });

                    no.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View v) {
                            alertDialog.dismiss();
                        }
                    });
                }
                return false;
            }
        });
    }

    private void expandAll(){
        int count = adapter.getGroupCount();
        for(int i = 0; i < count; i++)
        {
            myList.expandGroup(i);
        }
    }
}
