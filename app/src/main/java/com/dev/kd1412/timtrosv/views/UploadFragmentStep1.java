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

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.dev.kd1412.timtrosv.R;
import com.dev.kd1412.timtrosv.databinding.FragmentUploadStep1Binding;
import com.dev.kd1412.timtrosv.model.Room;


public class UploadFragmentStep1 extends Fragment implements View.OnClickListener {
    private FragmentUploadStep1Binding uploadStep1Binding;
    private String room_address ;
    private Room room = new Room();

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

    private void getFormValues() {
        String district = uploadStep1Binding.edtDistrict.getText().toString();
        String city = uploadStep1Binding.edtCity.getText().toString();
        String address = uploadStep1Binding.edtAddress.getText().toString();


        if (city.equals("")) {
            Toast.makeText(requireContext(), "Vui lòng chọn Tỉnh/TP", Toast.LENGTH_SHORT).show();
        } else if (district.equals("")) {
            Toast.makeText(requireContext(), "Vui lòng chọn Quận/Huyện", Toast.LENGTH_SHORT).show();
        } else if (address.equals("")) {
            Toast.makeText(requireContext(), "Vui lòng nhập địa chỉ", Toast.LENGTH_SHORT).show();
        }else {
            room_address = ""+ address + ", " + district + ", " + city;
            room.setmLocation(room_address);

            Navigation.findNavController(requireView()).navigate(UploadFragmentStep1Directions
                    .actionUploadFragmentStep1ToUploadFragmentStep2(room));
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_cancel:
                Navigation.findNavController(requireView()).navigateUp();
                break;
            case R.id.fab_next:
                getFormValues();
                Log.d("TAG", "onClick: " + room.mLocation + " get value: " + room_address);
                break;
        }
    }
}