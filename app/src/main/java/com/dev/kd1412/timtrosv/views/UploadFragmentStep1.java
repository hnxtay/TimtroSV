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
import android.widget.ArrayAdapter;

import com.dev.kd1412.timtrosv.R;
import com.dev.kd1412.timtrosv.databinding.FragmentUploadStep1Binding;

import java.util.List;


public class UploadFragmentStep1 extends Fragment implements View.OnClickListener {
    private FragmentUploadStep1Binding uploadStep1Binding;

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
        // Inflate the layout for this fragment
        uploadStep1Binding = DataBindingUtil.inflate(inflater, R.layout.fragment_upload_step1, container, false);

        uploadStep1Binding.fabCancel.setOnClickListener(this);
        uploadStep1Binding.fabNext.setOnClickListener(this);

        ArrayAdapter<String> cityAdapter = new ArrayAdapter<>(getContext(), R.layout.dropdown_menu_item
                , getResources().getStringArray(R.array.city));
        ArrayAdapter<String> districtAdapter = new ArrayAdapter<>(getContext(), R.layout.dropdown_menu_item
                , getResources().getStringArray(R.array.district));

        uploadStep1Binding.edtCity.setAdapter(cityAdapter);
        uploadStep1Binding.edtDistrict.setAdapter(districtAdapter);

        return uploadStep1Binding.getRoot();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_cancel:
                Navigation.findNavController(requireView()).navigateUp();
                break;
            case R.id.fab_next:
                Navigation.findNavController(requireView()).navigate(R.id.action_uploadFragmentStep1_to_uploadFragmentStep2);
                break;
        }
    }
}