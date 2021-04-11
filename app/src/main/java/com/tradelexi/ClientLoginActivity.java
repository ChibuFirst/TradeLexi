package com.tradelexi;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.tradelexi.client.ClientActivity;
import com.tradelexi.databinding.ActivityClientLoginBinding;
import com.tradelexi.util.FunctionUtil;
import com.tradelexi.util.InputValidation;

public class ClientLoginActivity extends AppCompatActivity {

    private ActivityClientLoginBinding binding;
    private Vibrator vibrator;
    private FunctionUtil func;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityClientLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        func = new FunctionUtil();

        binding.textInputEmail.getEditText().addTextChangedListener(new InputValidation(binding.textInputEmail));
        binding.textInputPassword.getEditText().addTextChangedListener(new InputValidation(binding.textInputPassword));
        binding.buttonLogin.setOnClickListener(view -> validateInput());
        binding.textSignUp.setOnClickListener(view -> startActivity(new Intent(ClientLoginActivity.this, ClientSignUpActivity.class)));
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
            startActivity(new Intent(this, ClientActivity.class));
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