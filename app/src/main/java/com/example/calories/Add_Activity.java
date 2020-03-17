package com.example.calories;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Add_Activity extends Activity {
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> search_list;
    private DatabaseReference database;
    public String[] Array = new String[]{"овощи", "фрукты и ягоды"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_activity);
        init();
    }

    private void init(){
        listView = findViewById(R.id.list);
        search_list = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, search_list);
        for (String products_key:Array) {
            database = FirebaseDatabase.getInstance().getReference(products_key);
            getDataFromDB();
        }
    }

    private void getDataFromDB(){
        database.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    Products product = ds.getValue(Products.class);
                    assert product != null;
                    search_list.add(ds.getKey());
                    search_list.add(product.getКалорийность().toString());
                }
                listView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void onClick_Back(View view) {
        finish();
    }
}
