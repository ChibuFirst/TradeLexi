package com.tradelexi.sp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.tradelexi.databinding.ActivityAccountSetupBinding;

public class AccountSetupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityAccountSetupBinding binding = ActivityAccountSetupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    public void onBackPressed() {
        new MaterialAlertDialogBuilder(this)
                .setTitle("Discard changes?")
                .setMessage("Are you sure to discard changes permanently?")
                .setPositiveButton("Discard", (dialogInterface, i) -> super.onBackPressed())
                .setNegativeButton("No", (dialogInterface, i) -> dialogInterface.dismiss())
                .setCancelable(false)
                .show();
    }
}