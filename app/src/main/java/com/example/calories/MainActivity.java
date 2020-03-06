package com.example.calories;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private TextView text_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text_date = findViewById(R.id.textView6);
        // Текущее время
        Date currentDate = new Date();
        // Форматирование времени как "день недели.день.месяц"
        DateFormat dateFormat = new SimpleDateFormat("EEEE.dd.MMMM", Locale.getDefault());
        String dateText = dateFormat.format(currentDate);

        text_date.setText(dateText);
    }

    public void onClick_Today(View view) {
    }

    public void onClick_History(View view) {
    }

    public void onClick_Parameters(View view) {
    }

    public void onClick_Settings(View view) {
    }
}
