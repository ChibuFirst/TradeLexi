package com.tradelexi.sp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.FragmentNavigator;
import androidx.transition.Transition;
import androidx.transition.TransitionInflater;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tradelexi.R;
import com.tradelexi.databinding.FragmentSeventhSetupBinding;

public class SeventhSetupFragment extends Fragment {

    private FragmentSeventhSetupBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Transition transition = TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move);
        setSharedElementEnterTransition(transition);
        setSharedElementReturnTransition(transition);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSeventhSetupBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonAdd.setOnClickListener(v -> {
            binding.constraint1.setVisibility(View.VISIBLE);
            binding.buttonAdd2.setVisibility(View.VISIBLE);
            binding.buttonAdd.setVisibility(View.GONE);
        });

        binding.buttonAdd2.setOnClickListener(v -> {
            binding.constraint2.setVisibility(View.VISIBLE);
            binding.buttonAdd2.setVisibility(View.GONE);
        });

        FragmentNavigator.Extras extras1 = new FragmentNavigator.Extras.Builder()
                .addSharedElement(binding.imageProgress, "progress6")
                .build();
        FragmentNavigator.Extras extras2 = new FragmentNavigator.Extras.Builder()
                .addSharedElement(binding.imageProgress, "progress8")
                .build();
        binding.buttonBack.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.action_seventhSetupFragment_to_sixthSetupFragment, null, null, extras1));
        binding.buttonNext.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.action_seventhSetupFragment_to_eighthSetupFragment, null, null, extras2));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}