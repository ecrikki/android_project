package com.example.calories;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class Parameter_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parameter_activity);

        BottomNavigationView menu = findViewById(R.id.menu);
        menu.setSelectedItemId(R.id.parameters);
        menu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.today){
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                }
                else if(item.getItemId() == R.id.history){
                    startActivity(new Intent(getApplicationContext(), History_Activity.class));
                    overridePendingTransition(0, 0);
                    return true;
                }
                else if(item.getItemId() == R.id.parameters){
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

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setLogo(R.drawable.logo_small);
    }

    public void onClick_Calc(View view) {
        TextView tv = (TextView)findViewById(R.id.tvResult);

        EditText ves = (EditText)findViewById(R.id.editText);
        int ves_int = Integer.parseInt(ves.getText().toString());
        EditText rost = (EditText)findViewById(R.id.editText2);
        int rost_int = Integer.parseInt(rost.getText().toString());
        EditText vozrast = (EditText)findViewById(R.id.editText3);
        int vozrast_int = Integer.parseInt(vozrast.getText().toString());

        RadioButton mm = (RadioButton)findViewById(R.id.radio_m);
//        RadioButton ww = (RadioButton)findViewById(R.id.radio_w);


        RadioGroup rgActiv = (RadioGroup)findViewById(R.id.radioGroup2);
        int radioButtonID = rgActiv.getCheckedRadioButtonId();

        View radioButton = rgActiv.findViewById(radioButtonID);
        int activnost = rgActiv.indexOfChild(radioButton);
        double BMR = 0;
        double level = 0;
        double answer = 0;
        boolean man;
        if (mm.isChecked())
        {
            // man
            man=true;
            BMR = 88.36 + (13.4 * ves_int) + (4.8 * rost_int) - (5.7 * vozrast_int);
        } else
            {
                // women
            man=false;
            BMR = 447.6 + (9.2 * ves_int) + (3.1 * rost_int) - (4.3 * vozrast_int);
        }

        if (activnost == 0)
        {
            level = 1.2;
        }
        else if (activnost == 1)
        {
            level = 1.375;
        }
        else if (activnost == 2)
        {
            level = 1.55;
        }
        else if (activnost == 3)
        {
            level = 1.725;
        }
        else
        {
            level = 1.9;
        }
//        rgActiv.getCheckedRadioButtonId();
        //String s = (man)?"men":"women";
        //s = s + " "+ (new Integer(activnost)).toString();
        answer = BMR * level;
        String s = "";
        s = (new Integer((int)Math.round(answer))).toString();
        tv.setText(s);

    }
}
