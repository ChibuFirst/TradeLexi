package com.tradelexi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.tradelexi.databinding.ActivitySignUpBinding;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySignUpBinding binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonClient.setOnClickListener(view -> startActivity(new Intent(SignUpActivity.this, ClientSignUpActivity.class)));
        binding.buttonServiceProvider.setOnClickListener(view -> startActivity(new Intent(SignUpActivity.this, SpSignUpActivity.class)));
        binding.linearLogin.setOnClickListener(view -> {
            startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
            SignUpActivity.this.finish();
        });
    }
}