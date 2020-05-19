package com.example.calories;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class History_Activity extends AppCompatActivity {

    SharedPreferences sPref;
    ArrayList<String> labels = new ArrayList<>();
    ArrayList<BarEntry> data = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_activity);

        BottomNavigationView menu = findViewById(R.id.menu);
        menu.setSelectedItemId(R.id.history);
        menu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.today){
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                }
                else if(item.getItemId() == R.id.history){
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

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setLogo(R.drawable.logo_small);

        BarChart chart = findViewById(R.id.chart);
        Get_statistic();
        Diagram_Format dataSet = new Diagram_Format(data, "kkal");
        dataSet.setColors(
                ContextCompat.getColor(chart.getContext(), R.color.diagram),
                ContextCompat.getColor(chart.getContext(), R.color.diagram2)
        );
      //  BarDataSet dataSet = new BarDataSet(data, "kkal");
      //  dataSet.setColors(ColorTemplate.PASTEL_COLORS);
        dataSet.setValueTextColor(Color.BLACK);
        dataSet.setValueTextSize(14f);

        XAxis xAxis = chart.getXAxis();
        xAxis.setTextSize(13f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setYOffset(0);
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return labels.get((int) value);
            }
        });

        BarData barData = new BarData(dataSet);
        LimitLine line = new LimitLine(100);
        chart.getAxisLeft().addLimitLine(line);
        chart.getAxisRight().setEnabled(false);
        chart.getAxisLeft().setEnabled(false);
        chart.setFitBars(true);
        chart.getXAxis().setDrawGridLines(false);
        chart.setData(barData);
        chart.getLegend().setEnabled(false);
        chart.getDescription().setEnabled(false);
        chart.animateY(2500);
    }

    private void Get_statistic() {
        sPref = getSharedPreferences("statistic", MODE_PRIVATE);
        DateFormat dateFormat = new SimpleDateFormat("dd MMMM", Locale.getDefault());
        int j = 0;
        int start = 30;
        for (int i = 13; i >= 0; i--){
            Calendar c = Calendar.getInstance();
            c.add(Calendar.DAY_OF_MONTH, -i);
            String first_date = dateFormat.format(c.getTime());
            if(sPref.getInt(first_date, -1) != -1){
                start = i;
                System.out.println(start);
                break;
            }
        }
        for (int i = start; i >= 0; i--){
            Calendar c = Calendar.getInstance();
            c.add(Calendar.DAY_OF_MONTH, -i);
            String date_statistic = dateFormat.format(c.getTime());
            int kkal = sPref.getInt(date_statistic, 0);
            data.add(new BarEntry(j, kkal));
            labels.add(date_statistic);
            j++;
        }
    }
}
