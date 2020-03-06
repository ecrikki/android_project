package com.example.calories;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.annotation.Nullable;

public class Logo_Activity extends Activity {
    private Animation Logo_anim;
    private ImageView Logo_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logo_activity);
        init();
        startMainActivity();
    }

    private void init(){
        //загружаем анимацию в переменную
        Logo_anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.logo_anim);
        //находим картинку
        Logo_image = findViewById(R.id.imageView);
        //Запускаем анимацию
        Logo_image.startAnimation(Logo_anim);
    }

    private void startMainActivity() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent i = new Intent(Logo_Activity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        }).start();
    }
}
