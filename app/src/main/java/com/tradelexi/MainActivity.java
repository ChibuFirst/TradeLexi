package com.tradelexi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.animation.AnimationUtils;

import com.tradelexi.databinding.ActivityMainBinding;
import com.tradelexi.onboarding.OnboardingActivity;
import com.tradelexi.util.Constants;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.imageLogo.startAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_down));
        binding.textSlogan.startAnimation(AnimationUtils.loadAnimation(this, R.anim.zoom_in));

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            SharedPreferences preferences = getSharedPreferences(Constants.SHARED_PREF_NAME, Context.MODE_PRIVATE);
            boolean isFirstTime = preferences.getBoolean(Constants.IS_FIRST_TIME, true);
            if (isFirstTime) {
                startActivity(new Intent(this, OnboardingActivity.class));
            } else {
                startActivity(new Intent(this, LoginActivity.class));
            }
            MainActivity.this.finish();
        }, 2500);
    }
}