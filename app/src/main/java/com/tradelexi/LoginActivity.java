package com.tradelexi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.tradelexi.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonClient.setOnClickListener(view -> {
            startActivity(new Intent(LoginActivity.this, ClientLoginActivity.class));
        });
        binding.buttonServiceProvider.setOnClickListener(view -> {
            startActivity(new Intent(LoginActivity.this, SpLoginActivity.class));
        });
        binding.linearSignUp.setOnClickListener(view -> {
            startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
            LoginActivity.this.finish();
        });
    }
}