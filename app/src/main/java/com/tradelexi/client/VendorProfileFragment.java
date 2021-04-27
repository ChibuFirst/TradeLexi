package com.tradelexi.client;

import android.content.Intent;
import android.net.Uri;
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
import android.widget.RatingBar;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.tradelexi.R;
import com.tradelexi.databinding.FragmentVendorProfileBinding;

public class VendorProfileFragment extends Fragment {

    private FragmentVendorProfileBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentVendorProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String contact = "09091706766";
        String whatsappContact = "2349091706766";

        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(requireContext());
        View rateView = LayoutInflater.from(requireContext()).inflate(R.layout.layout_rate, null);
        RatingBar ratingBar = rateView.findViewById(R.id.rating_bar);
        Button buttonSubmit = rateView.findViewById(R.id.button_submit);
        Button buttonClose = rateView.findViewById(R.id.button_close);
        builder.setView(rateView);
        builder.setCancelable(false);
        AlertDialog rateDialog = builder.create();

        binding.textRating.setText(String.valueOf(binding.vendorRating.getRating()));
        binding.textBack.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.action_vendorProfileFragment_to_homeFragment));
        binding.buttonWhatsapp.setOnClickListener(v -> {
            Intent whatsappIntent = new Intent(Intent.ACTION_VIEW);
            whatsappIntent.setData(Uri.parse("https://api.whatsapp.com/send?phone=" + whatsappContact));
            startActivity(whatsappIntent);
        });
        binding.buttonCall.setOnClickListener(v -> {
            Intent phoneIntent = new Intent(Intent.ACTION_DIAL);
            phoneIntent.setData(Uri.parse("tel:" + contact));
            startActivity(phoneIntent);
        });
        binding.buttonRate.setOnClickListener(v -> rateDialog.show());
        buttonClose.setOnClickListener(v -> rateDialog.dismiss());
        buttonSubmit.setOnClickListener(v-> {
            binding.vendorRating.setRating(ratingBar.getRating());
            binding.textRating.setText(String.valueOf(ratingBar.getRating()));
            rateDialog.dismiss();
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}