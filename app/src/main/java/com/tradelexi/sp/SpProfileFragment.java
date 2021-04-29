package com.tradelexi.sp;

import android.content.Intent;
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
import android.widget.ImageView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.tradelexi.R;
import com.tradelexi.databinding.FragmentSpProfileBinding;

public class SpProfileFragment extends Fragment {

    private FragmentSpProfileBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = com.tradelexi.databinding.FragmentSpProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        View deleteView = LayoutInflater.from(requireContext()).inflate(R.layout.layout_delete_account, null);
        View statisticsView = LayoutInflater.from(requireContext()).inflate(R.layout.layout_statistics_1, null);
        AlertDialog dialog = new MaterialAlertDialogBuilder(requireContext()).setView(deleteView).setCancelable(false).create();

        binding.textStatistics.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.action_sp_profile_fragment_to_statisticsFragment));
//        binding.hint.setOnClickListener

        binding.textUpdateEmail.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.action_profileFragment_to_emailUpdateFragment));
        binding.textChangePassword.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.action_profileFragment_to_changePasswordFragment));
        binding.textDeleteAccount.setOnClickListener(v -> dialog.show());
        binding.textContactUs.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.action_profileFragment_to_contactUsFragment));

//        ImageView hint1 = statisticsView.findViewById(R.id.hint);
//        hint1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(this, );
//            }
//        });

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