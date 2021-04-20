package com.tradelexi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;

import com.google.android.material.textfield.TextInputLayout;
import com.tradelexi.client.ClientActivity;
import com.tradelexi.databinding.ActivityForgotPasswordBinding;
import com.tradelexi.util.FunctionUtil;
import com.tradelexi.util.InputValidation;

public class ForgotPasswordActivity extends AppCompatActivity {

    private ActivityForgotPasswordBinding binding;
    private Vibrator vibrator;
    private FunctionUtil func;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityForgotPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        func = new FunctionUtil();

        binding.textInputEmail.getEditText().addTextChangedListener(new InputValidation(binding.textInputEmail));
        binding.buttonReset.setOnClickListener(view -> validateInput());
    }

    private void validateInput() {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (binding.textInputEmail.getEditText().getText().toString().trim().isEmpty()) {
            handleErrors(binding.textInputEmail, getString(R.string.error_field_required));
        } else if (!binding.textInputEmail.getEditText().getText().toString().trim().matches(emailPattern)) {
            handleErrors(binding.textInputEmail, getString(R.string.error_invalid_email));
        } else {
            func.displaySnackBar(binding.getRoot(), "Ready to send password reset link.");
            /*startActivity(new Intent(this, LoginActivity.class));
            finish();*/
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