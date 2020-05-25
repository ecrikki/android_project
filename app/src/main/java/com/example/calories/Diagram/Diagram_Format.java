package com.example.calories.Diagram;

import com.example.calories.History_Activity;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.List;

public class Diagram_Format extends BarDataSet {

    public Diagram_Format(List<BarEntry> yVals, String label) {
        super(yVals, label);
    }

    @Override
    public int getColor(int index) {
        int sum_param = History_Activity.preferences.getInt("Сумма_расч", 0);
        if(sum_param != 0) {
            if (getEntryForIndex(index).getY() < sum_param)
                return mColors.get(0);
            else
                return mColors.get(1);
        }
        else {
            return mColors.get(0);
        }
    }
}
