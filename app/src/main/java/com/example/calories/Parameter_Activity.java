package com.example.calories;


import android.content.Intent;
import android.content.SharedPreferences;
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

    private TextView tv;
    private RadioButton mm;
    private EditText ves;
    private EditText rost;
    private EditText vozrast;
    private RadioGroup rgActiv;

    SharedPreferences sPref;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parameter_activity);

        mm = findViewById(R.id.radio_m);
        tv = findViewById(R.id.tvResult);

        ves = findViewById(R.id.editText);
        rost = findViewById(R.id.editText2);
        vozrast = findViewById(R.id.editText3);

        rgActiv = findViewById(R.id.radioGroup2);

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
                else if(item.getItemId() == R.id.info){
                    startActivity(new Intent(getApplicationContext(), Info_Activity.class));
                    overridePendingTransition(0, 0);
                    return true;
                }
                return false;
            }
        });

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setLogo(R.drawable.logo_small);

        Get_data();
    }

    public void onClick_Calc(View view) {

        int ves_int = 0;
        int rost_int = 0;
        int vozrast_int = 0;

        if(ves.getText().length() != 0) {
            ves_int = Integer.parseInt(ves.getText().toString());
        }
        if(rost.getText().length() != 0) {
            rost_int = Integer.parseInt(rost.getText().toString());
        }
        if(vozrast.getText().length() != 0) {
            vozrast_int = Integer.parseInt(vozrast.getText().toString());
        }

        View radioButton = rgActiv.findViewById(rgActiv.getCheckedRadioButtonId());
        System.out.println(rgActiv.getCheckedRadioButtonId());
        int activnost = rgActiv.indexOfChild(radioButton);

        double BMR = 0;
        double level;
        double answer;
        if(ves_int != 0 & rost_int != 0 & vozrast_int != 0) {
            if (mm.isChecked()) {
                // man
                BMR = 88.36 + (13.4 * ves_int) + (4.8 * rost_int) - (5.7 * vozrast_int);
            } else {
                // women
                BMR = 447.6 + (9.2 * ves_int) + (3.1 * rost_int) - (4.3 * vozrast_int);
            }
        }
        if (activnost == 0) {
            level = 1.2;
        } else if (activnost == 1) {
            level = 1.375;
        } else if (activnost == 2) {
            level = 1.55;
        } else if (activnost == 3) {
            level = 1.725;
        } else {
            level = 1.9;
        }

        answer = BMR * level;
        String s = (Integer.valueOf((int) Math.round(answer))).toString();
        tv.setText(s);

        Save_data((int) Math.round(answer), ves_int, rost_int, vozrast_int);
    }

    private void Save_data(Integer answer, Integer ves, Integer rost, Integer vozrast){
        sPref = getSharedPreferences("parameters", MODE_PRIVATE);
        SharedPreferences.Editor editor = sPref.edit();
        editor.putInt("Сумма_расч", answer);
        editor.putInt("вес", ves);
        editor.putInt("рост", rost);
        editor.putInt("возраст", vozrast);
        editor.putBoolean("Gender", mm.isChecked());
        editor.putInt("Active", rgActiv.getCheckedRadioButtonId());
        editor.apply();
    }

    public void Get_data(){
        sPref = getSharedPreferences("parameters", MODE_PRIVATE);
        if (sPref.getInt("вес", 0) != 0) {
            ves.setText(String.valueOf(sPref.getInt("вес", 0)));
        }
        if (sPref.getInt("рост", 0) != 0) {
            rost.setText(String.valueOf(sPref.getInt("рост", 0)));
        }
        if (sPref.getInt("возраст", 0) != 0) {
            vozrast.setText(String.valueOf(sPref.getInt("возраст", 0)));
        }
        mm.setChecked(sPref.getBoolean("Gender", false));
        RadioButton rb = rgActiv.findViewById(sPref.getInt("Active", 2131230954));
        rb.setChecked(true);
        tv.setText(String.valueOf(sPref.getInt("Сумма_расч", 0)));
    }
}
