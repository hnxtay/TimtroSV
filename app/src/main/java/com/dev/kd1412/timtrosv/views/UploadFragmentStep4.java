
/*
 * Copyright (c) 2020.  by kd1412
 */

/*
 * Copyright (c) 2020.  by kd1412
 */

package com.dev.kd1412.timtrosv.views;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dev.kd1412.timtrosv.R;
import com.dev.kd1412.timtrosv.databinding.FragmentUploadStep4Binding;

public class UploadFragmentStep4 extends Fragment {
    private FragmentUploadStep4Binding uploadStep4Binding;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requireActivity().getOnBackPressedDispatcher().addCallback(new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Navigation.findNavController(requireView()).navigateUp();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        uploadStep4Binding = DataBindingUtil.inflate(inflater,R.layout.fragment_upload_step4,container,false);

        uploadStep4Binding.fabBack.setOnClickListener(v -> {
            Navigation.findNavController(requireView()).navigateUp();
        });
        return uploadStep4Binding.getRoot();
    }
}