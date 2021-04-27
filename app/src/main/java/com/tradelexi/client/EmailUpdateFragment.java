package com.tradelexi.client;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.textfield.TextInputLayout;
import com.tradelexi.R;
import com.tradelexi.databinding.FragmentEmailUpdateBinding;
import com.tradelexi.util.FunctionUtil;
import com.tradelexi.util.InputValidation;

public class EmailUpdateFragment extends Fragment {

    private FragmentEmailUpdateBinding binding;
    private Vibrator vibrator;
    private FunctionUtil func;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentEmailUpdateBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        vibrator = (Vibrator) requireContext().getSystemService(Context.VIBRATOR_SERVICE);
        func = new FunctionUtil();

        binding.textInputOldEmail.getEditText().addTextChangedListener(new InputValidation(binding.textInputOldEmail));
        binding.textInputNewEmail.getEditText().addTextChangedListener(new InputValidation(binding.textInputNewEmail));
        binding.textInputVerifyNewEmail.getEditText().addTextChangedListener(new InputValidation(binding.textInputVerifyNewEmail));
        binding.buttonUpdate.setOnClickListener(v -> validateInput());
    }

    private void validateInput() {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (binding.textInputOldEmail.getEditText().getText().toString().trim().isEmpty()) {
            handleErrors(binding.textInputOldEmail, getString(R.string.error_field_required));
        } else if (!binding.textInputOldEmail.getEditText().getText().toString().trim().matches(emailPattern)) {
            handleErrors(binding.textInputOldEmail, getString(R.string.error_invalid_email));
        } else if (binding.textInputNewEmail.getEditText().getText().toString().trim().isEmpty()) {
            handleErrors(binding.textInputNewEmail, getString(R.string.error_field_required));
        } else if (!binding.textInputNewEmail.getEditText().getText().toString().trim().matches(emailPattern)) {
            handleErrors(binding.textInputNewEmail, getString(R.string.error_invalid_email));
        } else {
            func.displayToast(binding.getRoot(), "Ready to update email");
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}