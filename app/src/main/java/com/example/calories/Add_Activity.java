package com.example.calories;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.calories.Class.Class_prod;
import com.example.calories.Class.Prod;
import com.example.calories.ViewHolder_Adapter.ProductsAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Add_Activity extends Activity {
    DatabaseReference ref;
    RecyclerView recyclerView;
    SearchView searchView;
    ArrayList<Class_prod> prod_type;
    ArrayList<Prod> product;
    String[] Array = new String[]{"овощи", "фрукты и ягоды"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_activity);

        recyclerView = findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        prod_type = new ArrayList<>();
        searchView = findViewById(R.id.searchView);

        for (String products_key : Array) {
            ref = FirebaseDatabase.getInstance().getReference(products_key);
            getDataFromDB();
        }
    }

    protected void getDataFromDB(){
        if(ref != null){
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    product = new ArrayList<>();
                    if(dataSnapshot.exists()){
                        String p = dataSnapshot.getKey();
                        for(DataSnapshot ds: dataSnapshot.getChildren()){
                            product.add(ds.getValue(Prod.class));
                        }
                        Class_prod i = new Class_prod(p, product);
                        prod_type.add(i);

                        ProductsAdapter adapter = new ProductsAdapter(prod_type);
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
        ArrayList<Class_prod> mList = new ArrayList<>();
        for(Class_prod object:prod_type){
            ArrayList<Prod> myList = new ArrayList<>();
            String title = object.getTitle();
            List p = object.getItems();
            for(Object pr: p){
                Prod product = (Prod) pr;
                if(product.getId_product().toLowerCase().contains(str.toLowerCase())){
                    myList.add(product);
                }
            }
            Class_prod i = new Class_prod(title, myList);
            mList.add(i);
            System.out.println(mList);
        }
        ProductsAdapter adapter = new ProductsAdapter(mList);
        recyclerView.setAdapter(adapter);
    }
}
