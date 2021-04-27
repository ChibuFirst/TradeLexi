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
import com.tradelexi.databinding.FragmentChangePasswordBinding;
import com.tradelexi.util.FunctionUtil;
import com.tradelexi.util.InputValidation;

public class ChangePasswordFragment extends Fragment {

    private FragmentChangePasswordBinding binding;
    private Vibrator vibrator;
    private FunctionUtil func;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentChangePasswordBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        vibrator = (Vibrator) requireContext().getSystemService(Context.VIBRATOR_SERVICE);
        func = new FunctionUtil();

        binding.textInputOldPassword.getEditText().addTextChangedListener(new InputValidation(binding.textInputOldPassword));
        binding.textInputNewPassword.getEditText().addTextChangedListener(new InputValidation(binding.textInputNewPassword));
        binding.textInputConfirmPassword.getEditText().addTextChangedListener(new InputValidation(binding.textInputConfirmPassword));
        binding.buttonUpdate.setOnClickListener(v -> validateInput());
    }

    private void validateInput() {
        if (binding.textInputOldPassword.getEditText().getText().toString().trim().isEmpty()) {
            handleErrors(binding.textInputOldPassword, getString(R.string.error_field_required));
        } else if (binding.textInputNewPassword.getEditText().getText().toString().trim().isEmpty()) {
            handleErrors(binding.textInputNewPassword, getString(R.string.error_field_required));
        } else if (binding.textInputConfirmPassword.getEditText().getText().toString().trim().isEmpty()) {
            handleErrors(binding.textInputConfirmPassword, getString(R.string.error_field_required));
        } else if (!binding.textInputNewPassword.getEditText().getText().toString().equals(binding.textInputConfirmPassword.getEditText().getText().toString())) {
            handleErrors(binding.textInputConfirmPassword, getString(R.string.error_password_mismatch));
        } else {
            func.displayToast(binding.getRoot(), "Ready to update password.");
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