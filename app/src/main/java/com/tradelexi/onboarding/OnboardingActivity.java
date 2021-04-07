package com.tradelexi.onboarding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.tradelexi.SignUpActivity;
import com.tradelexi.adapter.OnboardingPagerAdapter;
import com.tradelexi.databinding.ActivityOnboardingBinding;
import com.tradelexi.util.Constants;

public class OnboardingActivity extends AppCompatActivity {

    private ActivityOnboardingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOnboardingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        OnboardingPagerAdapter adapter = new OnboardingPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FirstFragment());
        adapter.addFragment(new SecondFragment());
        adapter.addFragment(new ThirdFragment());
        binding.viewPager.setAdapter(adapter);
        binding.tabLayout.setupWithViewPager(binding.viewPager);

        binding.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageSelected(int position) {
                if (position == 2) {
                    binding.buttonSkip.setVisibility(View.GONE);
                    binding.buttonNext.setVisibility(View.GONE);
                    binding.buttonGetStarted.setVisibility(View.VISIBLE);
                } else {
                    binding.buttonSkip.setVisibility(View.VISIBLE);
                    binding.buttonNext.setVisibility(View.VISIBLE);
                    binding.buttonGetStarted.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {}
        });

        binding.buttonSkip.setOnClickListener(view -> finishOnboarding() );
        binding.buttonGetStarted.setOnClickListener(view -> finishOnboarding() );

        binding.buttonNext.setOnClickListener(view -> {
            if (binding.viewPager.getCurrentItem() == 2) {
                Toast.makeText(OnboardingActivity.this, "Last", Toast.LENGTH_SHORT).show();
            } else {
                binding.viewPager.setCurrentItem(binding.viewPager.getCurrentItem() + 1, true);
            }
        });
    }

    private void finishOnboarding() {
        SharedPreferences preferences = getSharedPreferences(Constants.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        preferences.edit().putBoolean(Constants.IS_FIRST_TIME, false).apply();
        startActivity(new Intent(this, SignUpActivity.class));
        finish();
    }
}