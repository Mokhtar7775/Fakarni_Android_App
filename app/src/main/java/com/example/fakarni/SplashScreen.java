package com.example.fakarni;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.taskati.R;

@SuppressLint("CustomSplashScreen")
public class SplashScreen extends AppCompatActivity {
    public static int SPLASH_SCREEN = 5000;
    Animation topAnimation,bottomAnimation;
    ImageView imageView;
    TextView textView;
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        topAnimation = AnimationUtils.loadAnimation(this, R.anim.topanimation);
        bottomAnimation = AnimationUtils.loadAnimation(this, R.anim.bottomanimation);
        imageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.textView);
        textView.setAnimation(bottomAnimation);
        imageView.setAnimation(topAnimation);

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashScreen.this, LogIn.class);
            startActivity(intent);
        },SPLASH_SCREEN);

    }
}
