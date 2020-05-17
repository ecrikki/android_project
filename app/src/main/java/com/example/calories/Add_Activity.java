package com.example.calories;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SearchView;

import com.example.calories.Class.Class_prod;
import com.example.calories.Class.Prod;
import com.example.calories.Adapter.ProductsAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Add_Activity extends Activity implements SearchView.OnQueryTextListener, SearchView.OnCloseListener {

    private ProductsAdapter adapter;
    private ExpandableListView myList;
    private ArrayList<Class_prod> class_prodList = new ArrayList<>();
    DatabaseReference ref;
    String[] Array = new String[]{"овощи", "фрукты и ягоды"};
    String gramm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_activity);

        myList = findViewById(R.id.expandableList);
        SearchView search = findViewById(R.id.search);
        search.setIconifiedByDefault(false);
        search.setOnQueryTextListener(this);
        search.setOnCloseListener(this);

        for (String products_key : Array) {
            ref = FirebaseDatabase.getInstance().getReference(products_key);
            getDataFromDB();
        }
        chooseProd();
    }

    private void chooseProd(){
        myList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                final Prod selected = (Prod) adapter.getChild(groupPosition, childPosition);

                LayoutInflater inflater = LayoutInflater.from(Add_Activity.this);
                final View dialogView = inflater.inflate(R.layout.dialog, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(Add_Activity.this);
                builder.setView(dialogView);

                Button ok = dialogView.findViewById(R.id.ok);
                Button cancel = dialogView.findViewById(R.id.cancel);

                ImageView left = dialogView.findViewById(R.id.imageView3);
                ImageView right = dialogView.findViewById(R.id.imageView4);
                left.setImageResource(R.drawable.left_arrow);
                right.setImageResource(R.drawable.right_arrow);

                final AlertDialog alertDialog = builder.create();
                alertDialog.show();

                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent();
                        EditText userInput = dialogView.findViewById(R.id.input_text);
                        String gramm = String.valueOf(userInput.getText());
                        if (gramm.length() != 0) {
                            i.putExtra("prod", selected);
                            i.putExtra("gramm", gramm);
                            setResult(RESULT_OK, i);
                            alertDialog.dismiss();
                            finish();
                        }
                    }
                });

                cancel.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });

                left.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        EditText userInput = dialogView.findViewById(R.id.input_text);
                        String gramm = String.valueOf(userInput.getText());
                        if (gramm.length() != 0) {
                            int gr = Integer.parseInt(gramm);
                            gr = gr - 10;
                            if (gr > 0){
                                userInput.setText(Integer.toString(gr));
                            }
                            else{
                                userInput.setText(Integer.toString(0));
                            }
                        }
                        else{
                            userInput.setText(Integer.toString(0));
                        }
                    }
                });

                right.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        EditText userInput = dialogView.findViewById(R.id.input_text);
                        String gramm = String.valueOf(userInput.getText());
                        if (gramm.length() != 0) {
                            int gr = Integer.parseInt(gramm);
                            gr = gr + 10;
                            userInput.setText(Integer.toString(gr));
                        }
                        else{
                            userInput.setText(Integer.toString(10));
                        }
                    }
                });
                return false;
            }
        });
    }

    protected void getDataFromDB() {
        if (ref != null) {
            ref.addValueEventListener(new ValueEventListener() {

                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    ArrayList<Prod> product = new ArrayList<>();
                    if (dataSnapshot.exists()) {
                        String p = dataSnapshot.getKey();
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            product.add(ds.getValue(Prod.class));
                        }
                        Class_prod class_prod = new Class_prod(p, gramm, product);
                        class_prodList.add(class_prod);
                    }
                    adapter = new ProductsAdapter(Add_Activity.this, class_prodList);
                    myList.setAdapter(adapter);
                    expandAll();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(Add_Activity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void expandAll(){
        int count = adapter.getGroupCount();
        for(int i = 0; i < count; i++)
        {
            myList.expandGroup(i);
        }
    }

    @Override
    public boolean onClose() {
        adapter.filterData("");
        expandAll();
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        adapter.filterData(query);
        expandAll();
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        adapter.filterData(newText);
        expandAll();
        return false;
    }
}
