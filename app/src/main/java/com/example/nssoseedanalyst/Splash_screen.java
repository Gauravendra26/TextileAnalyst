package com.example.nssoseedanalyst;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Splash_screen extends AppCompatActivity {
    ImageView imgSplash,imgSplashLogo;
    TextView tvSplash,tvSplash1,tvMes,tvMes1;
    CardView cardSplash,cardSplashImg;
    Animation topanimantion,bottomanimation,middleanimation,center;
    Handler handler;
    LinearLayout lower,middle,upper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        init();

        topanimantion = AnimationUtils.loadAnimation(this, R.anim.topanimantion);
        middleanimation = AnimationUtils.loadAnimation(this, R.anim.middleanimation);
        bottomanimation = AnimationUtils.loadAnimation(this, R.anim.bottomanimation);
        center = AnimationUtils.loadAnimation(this, R.anim.center_out);


        upper.setAnimation(topanimantion);
        middle.setAnimation(center);
        lower.setAnimation(bottomanimation);


        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                SharedPreferences sh = getSharedPreferences("MyData", MODE_PRIVATE);
                Boolean Login_Status = sh.getBoolean("Login_Status", false);
                if (Login_Status) {
                    Intent intent = new Intent(Splash_screen.this, profile_page.class);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(Splash_screen.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }


            }
        }, 4000);

    }

    public void init(){

        imgSplashLogo = findViewById(R.id.imgSplashLogo);
//        imgSplash = findViewById(R.id.imgSplash);
        tvSplash = findViewById(R.id.tvSplash);
        tvSplash1 = findViewById(R.id.tvSplash1);
        tvMes = findViewById(R.id.tvMes);
        tvMes1 = findViewById(R.id.tvMes1);
        cardSplash = findViewById(R.id.cardSplash);
//        cardSplashImg = findViewById(R.id.cardSplashImg);
        lower = findViewById(R.id.lower);
        middle = findViewById(R.id.middle);
        upper = findViewById(R.id.upper);

    }
}