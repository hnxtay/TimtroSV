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
import android.widget.RadioButton;
import android.widget.Toast;

import com.dev.kd1412.timtrosv.R;
import com.dev.kd1412.timtrosv.databinding.FragmentUploadStep2Binding;
import com.dev.kd1412.timtrosv.model.Room;

import java.util.ArrayList;
import java.util.List;

public class UploadFragmentStep2 extends Fragment implements View.OnClickListener {
    FragmentUploadStep2Binding uploadStep2Binding;
    private Room room;
    private RadioButton rdb_news_type, rdb_room_type;
    private int roomTypeId, newsTypeId, roomPrice, roomAcreage;
    private String newsType, roomType, roomDescription;
    private List<String> utilitiesList = new ArrayList<>();
    private boolean newsChecked, roomChecked, priceChecked, acreageChecked, utilitiesChecked, descriptionChecked = false;

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
        uploadStep2Binding = DataBindingUtil.inflate(inflater, R.layout.fragment_upload_step2, container, false);

        uploadStep2Binding.fabBack.setOnClickListener(this);
        uploadStep2Binding.fabNext.setOnClickListener(this);


        room = UploadFragmentStep2Args.fromBundle(requireArguments()).getRoom();


        return uploadStep2Binding.getRoot();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_back:
                Navigation.findNavController(requireView()).navigateUp();
                break;
            case R.id.fab_next:
                getFormValues();
                Log.d("TAG", "onClick: " + room.mLocation + " " + room.mAcreage
                        + " " + room.mPrice + " " + room.mUtilities);
        }
    }

    private void getFormValues() {

        newsTypeId = uploadStep2Binding.grNewsType.getCheckedRadioButtonId();
        Log.d("TAG", "getFormValues: " + newsTypeId);
        if (newsTypeId != -1) {
            rdb_news_type = uploadStep2Binding.getRoot().findViewById(newsTypeId);
            newsType = rdb_news_type.getText().toString();
            Log.d("TAG", "getFormValues: " + newsType);
            newsChecked = true;
        } else {
            Toast.makeText(requireContext(), "Vui lòng chọn loại tin ", Toast.LENGTH_SHORT).show();
        }

        roomTypeId = uploadStep2Binding.grRoomType.getCheckedRadioButtonId();
        Log.d("TAG", "getFormValues: " + roomTypeId);
        if (roomTypeId != -1) {
            rdb_room_type = uploadStep2Binding.getRoot().findViewById(roomTypeId);
            roomType = rdb_room_type.getText().toString();
            Log.d("TAG", "getFormValues: " + roomType);
            roomChecked = true;
        } else {
            Toast.makeText(requireContext(), "Vui lòng chọn loại phòng", Toast.LENGTH_SHORT).show();
        }

        if (uploadStep2Binding.edtRoomPrice.getText().toString().equals("")) {
            Toast.makeText(requireContext(), "Vui lòng nhập giá phòng", Toast.LENGTH_SHORT).show();
        } else {
            roomPrice = Integer.parseInt(uploadStep2Binding.edtRoomPrice.getText().toString());
            room.setmPrice(roomPrice);
            priceChecked = true;
        }

        if (uploadStep2Binding.edtRoomAcreage.getText().toString().equals("")) {
            Toast.makeText(requireContext(), "Vui lòng nhập diện tích phòng", Toast.LENGTH_SHORT).show();
        } else {
            roomAcreage = Integer.parseInt(uploadStep2Binding.edtRoomAcreage.getText().toString());
            room.setmAcreage(roomAcreage);
            acreageChecked = true;
        }

        if (uploadStep2Binding.refrigerator.isChecked() && !utilitiesList.contains("refrigerator")) {
            utilitiesList.add("refrigerator");
        }
        if (uploadStep2Binding.airConditional.isChecked() && !utilitiesList.contains("air conditional")) {
            utilitiesList.add("air conditional");
        }
        if (uploadStep2Binding.wifi.isChecked() && !utilitiesList.contains("wifi")) {
            utilitiesList.add("wifi");
        }
        if (uploadStep2Binding.washingMachine.isChecked() && !utilitiesList.contains("washing machine")) {
            utilitiesList.add("washing machine");
        }
        if (uploadStep2Binding.freeTime.isChecked() && !utilitiesList.contains("free time")) {
            utilitiesList.add("free time");
        }
        if (uploadStep2Binding.parking.isChecked() && !utilitiesList.contains("park")) {
            utilitiesList.add("park");
        }
        if (uploadStep2Binding.kitchen.isChecked() && !utilitiesList.contains("kitchen")) {
            utilitiesList.add("kitchen");
        }
        if (uploadStep2Binding.wc.isChecked() && !utilitiesList.contains("wc")) {
            utilitiesList.add("wc");
        }
        if (utilitiesList.size() != 0) {
            room.setmUtilities(utilitiesList);
            utilitiesChecked = true;
        } else {
            Toast.makeText(requireContext(), "Vui lòng chọn tiện ích", Toast.LENGTH_SHORT).show();
        }
        if (uploadStep2Binding.edtDescription.getText().toString().equals("")){
            Toast.makeText(requireContext(), "Vui lòng nhập mô tả", Toast.LENGTH_SHORT).show();
        }else {
            roomDescription = uploadStep2Binding.edtDescription.getText().toString();
            room.setmDescription(roomDescription);
            Log.d("TAG", "getFormValues: " + roomDescription);
            descriptionChecked = true;
        }

        if (newsChecked && newsChecked && priceChecked && acreageChecked && utilitiesChecked && descriptionChecked){
            Navigation.findNavController(requireView()).navigate(UploadFragmentStep2Directions.actionUploadFragmentStep2ToUploadFragmentStep3(room));
        }
    }

}