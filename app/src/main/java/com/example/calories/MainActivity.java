package com.example.calories;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity
{
    private TextView text_date;
    private Button b1, b2, b3, b4;

    private int actText, actBk;
    private  int naText , naBk;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text_date = findViewById(R.id.textView6);
        // Текущее время
        Date currentDate = new Date();
        // Форматирование времени как "день недели.день.месяц"
        DateFormat dateFormat = new SimpleDateFormat("EEEE.dd.MMMM", Locale.getDefault());
        String dateText = dateFormat.format(currentDate);

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
        startActivity(i);
    }
}
