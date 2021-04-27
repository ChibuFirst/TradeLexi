package com.tradelexi.client;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
import com.tradelexi.databinding.FragmentContactUsBinding;
import com.tradelexi.util.FunctionUtil;
import com.tradelexi.util.InputValidation;

public class ContactUsFragment extends Fragment {

    private FragmentContactUsBinding binding;
    private Vibrator vibrator;
    private FunctionUtil func;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentContactUsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        vibrator = (Vibrator) requireContext().getSystemService(Context.VIBRATOR_SERVICE);
        func = new FunctionUtil();

        binding.textInputSubject.getEditText().addTextChangedListener(new InputValidation(binding.textInputSubject));
        binding.textInputMessage.getEditText().addTextChangedListener(new InputValidation(binding.textInputMessage));
        binding.buttonSend.setOnClickListener(v -> validateInput());
    }

    private void validateInput() {
        if (binding.textInputSubject.getEditText().getText().toString().trim().isEmpty()) {
            handleErrors(binding.textInputSubject, getString(R.string.error_field_required));
        } else if (binding.textInputMessage.getEditText().getText().toString().trim().isEmpty()) {
            handleErrors(binding.textInputMessage, getString(R.string.error_field_required));
        } else {
            sendMail(binding.textInputSubject.getEditText().getText().toString(), binding.textInputMessage.getEditText().getText().toString());
        }
    }

    private void sendMail(String subject, String message) {
        Intent mailIntent = new Intent(Intent.ACTION_SENDTO);
        mailIntent.setData(Uri.parse("mailto:"));
        mailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"info@tradelexi.com"});
        mailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        mailIntent.putExtra(Intent.EXTRA_TEXT, message);
        requireContext().startActivity(mailIntent);
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