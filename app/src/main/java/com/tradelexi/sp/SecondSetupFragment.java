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
import android.widget.ArrayAdapter;

import com.tradelexi.R;
import com.tradelexi.databinding.FragmentSecondSetupBinding;

import java.util.ArrayList;
import java.util.List;

public class SecondSetupFragment extends Fragment {

    private FragmentSecondSetupBinding binding;

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
        binding = FragmentSecondSetupBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<String> dayList = new ArrayList<>();
        dayList.add("DD");
        for (int i = 1; i <= 31; i++) {
            dayList.add(String.valueOf(i));
        }
        ArrayAdapter<String> dayAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, dayList);
        dayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerDay.setAdapter(dayAdapter);

        List<String> yearList = new ArrayList<>();
        yearList.add("YYYY");
        for (int i = 2021; i >= 1960; i--) {
            yearList.add(String.valueOf(i));
        }
        ArrayAdapter<String> yearAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, yearList);
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerYear.setAdapter(yearAdapter);

        FragmentNavigator.Extras extras1 = new FragmentNavigator.Extras.Builder()
                .addSharedElement(binding.imageProgress, "progress1")
                .build();
        FragmentNavigator.Extras extras2 = new FragmentNavigator.Extras.Builder()
                .addSharedElement(binding.imageProgress, "progress3")
                .build();
        binding.buttonBack.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.action_secondSetupFragment_to_firstSetupFragment, null, null, extras1));
        binding.buttonNext.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.action_secondSetupFragment_to_thirdSetupFragment, null, null, extras2));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}