package com.tradelexi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;

import com.google.android.material.textfield.TextInputLayout;
import com.tradelexi.databinding.ActivitySpLoginBinding;
import com.tradelexi.util.FunctionUtil;
import com.tradelexi.util.InputValidation;

public class SpLoginActivity extends AppCompatActivity {

    private ActivitySpLoginBinding binding;
    private Vibrator vibrator;
    private FunctionUtil func;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySpLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        func = new FunctionUtil();

        binding.textInputEmail.getEditText().addTextChangedListener(new InputValidation(binding.textInputEmail));
        binding.textInputPassword.getEditText().addTextChangedListener(new InputValidation(binding.textInputPassword));
        binding.buttonLogin.setOnClickListener(view -> validateInput());

        binding.linearSignUp.setOnClickListener(view -> {
            startActivity(new Intent(SpLoginActivity.this, SpSignUpActivity.class));
            SpLoginActivity.this.finish();
        });
    }

    private void validateInput() {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (binding.textInputEmail.getEditText().getText().toString().trim().isEmpty()) {
            handleErrors(binding.textInputEmail, getString(R.string.error_field_required));
        } else if (!binding.textInputEmail.getEditText().getText().toString().trim().matches(emailPattern)) {
            handleErrors(binding.textInputEmail, getString(R.string.error_invalid_email));
        } else if (binding.textInputPassword.getEditText().getText().toString().trim().isEmpty()) {
            handleErrors(binding.textInputPassword, getString(R.string.error_field_required));
        } else {
            func.displaySnackBar(binding.getRoot(), "You're good to go!");
        }
    }

    private void handleErrors(TextInputLayout textInputLayout, String errorMessage) {
        textInputLayout.setError(errorMessage);
        textInputLayout.startAnimation(func.shakeError());
        textInputLayout.requestFocus();
        vibrate();
    }

    private void vibrate() {
        vibrator.vibrate(50);
    }
}