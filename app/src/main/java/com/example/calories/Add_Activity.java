package com.example.calories;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Add_Activity extends Activity {
    DatabaseReference ref;
    ArrayList<Products> list;
    RecyclerView recyclerView;
    SearchView searchView;
    String[] Array = new String[]{"овощи", "фрукты и ягоды"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_activity);

        recyclerView = findViewById(R.id.rv);
        searchView = findViewById(R.id.searchView);
        list = new ArrayList<>();
        for (String products_key:Array) {
            ref = FirebaseDatabase.getInstance().getReference(products_key);
            getDataFromDB();
        }
    }

    protected void getDataFromDB(){
        if(ref != null){
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.exists()){
                        for(DataSnapshot ds: dataSnapshot.getChildren()){
                            list.add(ds.getValue(Products.class));
                        }
                        ProductsAdapter adapter = new ProductsAdapter(list);
                        recyclerView.setAdapter(adapter);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(Add_Activity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
        if(searchView != null){
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    search(newText);
                    return true;
                }
            });
        }
    }

    private void search(String str){
        ArrayList<Products> myList = new ArrayList<>();
        for(Products object:list){
            if(object.getId_product().toLowerCase().contains(str.toLowerCase())){
                myList.add(object);
            }
        }
        ProductsAdapter adapter = new ProductsAdapter(myList);
        recyclerView.setAdapter(adapter);
    }
}
