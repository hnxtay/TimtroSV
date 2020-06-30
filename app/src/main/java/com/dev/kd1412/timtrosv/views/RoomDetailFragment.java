package com.dev.kd1412.timtrosv.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;

import android.transition.Fade;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dev.kd1412.timtrosv.R;
import com.dev.kd1412.timtrosv.databinding.FragmentRoomDetailBinding;
import com.dev.kd1412.timtrosv.model.Room;

import static androidx.navigation.NavOptions.*;
import static com.dev.kd1412.timtrosv.activities.MainActivity.navController;

public class RoomDetailFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentRoomDetailBinding roomDetailBinding = DataBindingUtil
                .inflate(inflater, R.layout.fragment_room_detail, container, false);

        roomDetailBinding.toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onbackPress();
            }
        });

        return roomDetailBinding.getRoot();
    }public void onbackPress() {
        navController.popBackStack();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}