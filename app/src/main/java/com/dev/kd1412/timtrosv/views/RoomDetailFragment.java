/*
 * Copyright (c) 2020.  by kd1412
 */

package com.dev.kd1412.timtrosv.views;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;

import com.dev.kd1412.timtrosv.R;
import com.dev.kd1412.timtrosv.activities.DebugActivity;
import com.dev.kd1412.timtrosv.adapters.ImageAdapter;
import com.dev.kd1412.timtrosv.adapters.UtilitiesAdapter;
import com.dev.kd1412.timtrosv.databinding.ContactBottomSheetDialogBinding;
import com.dev.kd1412.timtrosv.databinding.FragmentRoomDetailsBinding;
import com.dev.kd1412.timtrosv.model.Room;
import com.dev.kd1412.timtrosv.model.User;
import com.dev.kd1412.timtrosv.model.Utilities;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class RoomDetailFragment extends Fragment {
    private Utilities utilities;
    private ArrayList<Utilities> utilitiesArrayList = new ArrayList<>();
    private UtilitiesAdapter utilitiesAdapter;
    private String phone_number = "0839874501";
    public static final String TAG = "RoomDetail";
    private FirebaseUser firebaseUser;
    private User user;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requireActivity().getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Navigation.findNavController(requireView()).navigateUp();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentRoomDetailsBinding roomDetailBinding = DataBindingUtil
                .inflate(inflater, R.layout.fragment_room_details, container, false);

        Room room = RoomDetailFragmentArgs.fromBundle(requireArguments()).getRoom();
        roomDetailBinding.setRoom(room);

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(room.mImg);
        arrayList.add(room.mImg);
        arrayList.add(room.mImg);
        Log.d(TAG, "onCreateView: " + arrayList);

        ImageAdapter imageAdapter = new ImageAdapter(arrayList);
        roomDetailBinding.viewPager.setAdapter(imageAdapter);
        for (int i = 0; i < room.getmUtilities().size(); ++i) {
            utilities = new Utilities(room.mUtilities.get(i));
            utilitiesArrayList.add(utilities);
        }
        utilitiesAdapter = new UtilitiesAdapter(utilitiesArrayList);
        roomDetailBinding.rcvUtilities.setAdapter(utilitiesAdapter);
        roomDetailBinding.rcvUtilities.setLayoutManager(new GridLayoutManager(requireContext(), 4));

        roomDetailBinding.toolbar.setOnClickListener(v -> onBackPress());
        roomDetailBinding.tvUtilities.setText("Tiện ích(" + room.getmUtilities().size() + ")");

        roomDetailBinding.fabContact.setOnClickListener(v -> {
            BottomSheetDialog dialog = new BottomSheetDialog(requireContext());
            View view = getLayoutInflater().inflate(R.layout.contact_bottom_sheet_dialog, null);
            dialog.setContentView(view);
            TextView btn_call = view.findViewById(R.id.btn_call);
            TextView btn_message = view.findViewById(R.id.btn_message);
            btn_call.setOnClickListener(v1 -> {
                if (checkPermission(Manifest.permission.CALL_PHONE,111)){
                    Intent intentCall = new Intent(Intent.ACTION_CALL);
                    intentCall.setData(Uri.parse("tel: " + phone_number));
                    requireContext().startActivity(intentCall);
                }
            });
            btn_message.setOnClickListener(v1 -> {
                Toast.makeText(requireContext(), "Features are in development process", Toast.LENGTH_SHORT).show();
            });
            dialog.show();
        });
        return roomDetailBinding.getRoot();
    }

    public void onBackPress() {
        Navigation.findNavController(requireView()).popBackStack();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
    public boolean checkPermission(String permission, int requestCode) {
        if (ContextCompat.checkSelfPermission(requireContext(), permission)
                == PackageManager.PERMISSION_DENIED) {
            // Requesting the permission
            ActivityCompat.requestPermissions(requireActivity(),
                    new String[] { permission },
                    requestCode);
            return false;
        }
        else {
            return true;
        }
    }
}