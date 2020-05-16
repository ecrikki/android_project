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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logo_activity);
        anim();
        startMainActivity();
    }

    private void anim(){
        //загружаем анимацию в переменную
        Animation logo_anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.logo_anim);
        //находим картинку
        ImageView logo_image = findViewById(R.id.imageView);
        //Запускаем анимацию
        logo_image.startAnimation(logo_anim);
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
