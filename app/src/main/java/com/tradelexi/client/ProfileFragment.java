package com.tradelexi.client;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.tradelexi.R;
import com.tradelexi.databinding.FragmentProfileBinding;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        View deleteView = LayoutInflater.from(requireContext()).inflate(R.layout.layout_delete_account, null);
        AlertDialog dialog = new MaterialAlertDialogBuilder(requireContext()).setView(deleteView).setCancelable(false).create();

        binding.textUpdateEmail.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.action_profileFragment_to_emailUpdateFragment));
        binding.textChangePassword.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.action_profileFragment_to_changePasswordFragment));
        binding.textDeleteAccount.setOnClickListener(v -> dialog.show());
        binding.textContactUs.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.action_profileFragment_to_contactUsFragment));

        Button buttonNo = deleteView.findViewById(R.id.button_no);
        buttonNo.setOnClickListener(v -> dialog.dismiss());
        //Button buttonYes = deleteView.findViewById(R.id.button_yes);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}