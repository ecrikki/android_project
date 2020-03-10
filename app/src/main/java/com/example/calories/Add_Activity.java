package com.example.calories;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class Add_Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_activity);
    }

    public void onClick_Back(View view) {
        finish();
    }
}
