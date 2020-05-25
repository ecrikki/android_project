package com.example.calories.Diagram;

import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.List;

public class Diagram_Format extends BarDataSet {

    public Diagram_Format(List<BarEntry> yVals, String label) {
        super(yVals, label);
    }

    @Override
    public int getColor(int index) {
        if(getEntryForIndex(index).getY() < 80)
            return mColors.get(0);
        else
            return mColors.get(1);
    }
}
