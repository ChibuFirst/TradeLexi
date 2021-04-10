package com.tradelexi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;

import com.google.android.material.textfield.TextInputLayout;
import com.tradelexi.databinding.ActivitySpSignUpBinding;
import com.tradelexi.util.FunctionUtil;
import com.tradelexi.util.InputValidation;

public class SpSignUpActivity extends AppCompatActivity {

    private ActivitySpSignUpBinding binding;
    private Vibrator vibrator;
    private FunctionUtil func;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySpSignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        func = new FunctionUtil();

        binding.textInputName.getEditText().addTextChangedListener(new InputValidation(binding.textInputName));
        binding.textInputEmail.getEditText().addTextChangedListener(new InputValidation(binding.textInputEmail));
        binding.textInputPassword.getEditText().addTextChangedListener(new InputValidation(binding.textInputPassword));
        binding.textInputConfirmPassword.getEditText().addTextChangedListener(new InputValidation(binding.textInputConfirmPassword));

        binding.linearLogin.setOnClickListener(view -> {
            startActivity(new Intent(SpSignUpActivity.this, SpLoginActivity.class));
            SpSignUpActivity.this.finish();
        });

        binding.buttonSignUp.setOnClickListener(view -> validateInputs());
    }

    private void validateInputs() {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (binding.textInputName.getEditText().getText().toString().isEmpty()) {
            handleErrors(binding.textInputName, getString(R.string.error_field_required));
        } else if (binding.textInputEmail.getEditText().getText().toString().isEmpty()) {
            handleErrors(binding.textInputEmail, getString(R.string.error_field_required));
        } else if (!binding.textInputEmail.getEditText().getText().toString().trim().matches(emailPattern)) {
            handleErrors(binding.textInputEmail, getString(R.string.error_invalid_email));
        } else if (binding.textInputPassword.getEditText().getText().toString().isEmpty()) {
            handleErrors(binding.textInputPassword, getString(R.string.error_field_required));
        } else if (binding.textInputPassword.getEditText().getText().toString().length() < 6) {
            handleErrors(binding.textInputPassword, getString(R.string.error_password_length));
        } else if (binding.textInputConfirmPassword.getEditText().getText().toString().isEmpty()) {
            handleErrors(binding.textInputConfirmPassword, getString(R.string.error_field_required));
        } else if (!binding.textInputPassword.getEditText().getText().toString().equals(binding.textInputConfirmPassword.getEditText().getText().toString())) {
            handleErrors(binding.textInputConfirmPassword, getString(R.string.error_password_mismatch));
        } else {
            startActivity(new Intent(this, SpLoginActivity.class));
            finish();
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