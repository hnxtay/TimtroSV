package com.dev.kd1412.timtrosv.views;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;

import com.dev.kd1412.timtrosv.R;
import com.dev.kd1412.timtrosv.adapters.ImageAdapter;
import com.dev.kd1412.timtrosv.adapters.UtilitiesAdapter;
import com.dev.kd1412.timtrosv.databinding.FragmentRoomDetailsBinding;
import com.dev.kd1412.timtrosv.model.Room;
import com.dev.kd1412.timtrosv.model.Utilities;

import java.util.ArrayList;

public class RoomDetailFragment extends Fragment {
    private Utilities utilities;
    private ArrayList<Utilities> utilitiesArrayList = new ArrayList<>();
    private UtilitiesAdapter utilitiesAdapter;
    public static final String TAG = "RoomDetail";

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
        Log.d(TAG, "onCreateView: " + arrayList);

        ImageAdapter imageAdapter = new ImageAdapter(arrayList);
        roomDetailBinding.viewPager.setAdapter(imageAdapter);
        for (int i = 0; i < room.getmUtilities().size(); ++i){
            utilities = new Utilities(room.mUtilities.get(i));
            utilitiesArrayList.add(utilities);
        }
        utilitiesAdapter = new UtilitiesAdapter(utilitiesArrayList);
        roomDetailBinding.rcvUtilities.setAdapter(utilitiesAdapter);
        roomDetailBinding.rcvUtilities.setLayoutManager(new GridLayoutManager(requireContext(),4));

        roomDetailBinding.toolbar.setOnClickListener(v -> onBackPress());

        return roomDetailBinding.getRoot();
    }

    public void onBackPress() {
        Navigation.findNavController(requireView()).popBackStack();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}