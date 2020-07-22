/*
 * Copyright (c) 2020.  by kd1412
 */

package com.dev.kd1412.timtrosv.views;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dev.kd1412.timtrosv.activities.MainActivity;
import com.dev.kd1412.timtrosv.adapters.OnItemClickListener;
import com.dev.kd1412.timtrosv.databinding.ActivityMainBinding;
import com.dev.kd1412.timtrosv.model.Room;
import com.dev.kd1412.timtrosv.R;
import com.dev.kd1412.timtrosv.model.Rooms;
import com.dev.kd1412.timtrosv.network.RoomService;
import com.dev.kd1412.timtrosv.network.RoomServiceApi;
import com.dev.kd1412.timtrosv.adapters.HomeRoomAdapter;
import com.dev.kd1412.timtrosv.databinding.FragmentHomeBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment implements OnItemClickListener {
    public ArrayList<Room> roomArrayList;
    private Room room;
    public HomeRoomAdapter roomAdapter;
    FragmentHomeBinding homeBinding;
    private static final String TAG = "HomeFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        homeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        initView();
        setRoomAdapter();

        return homeBinding.getRoot();
    }

    private void initView() {
        homeBinding.fabHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(requireView()).navigate(R.id.action_navigation_home_to_uploadFragmentStep1);
            }
        });
    }

    private void setRoomAdapter() {
        roomArrayList = new ArrayList();

        roomAdapter = new HomeRoomAdapter(roomArrayList, this);
        homeBinding.rcvHome.setAdapter(roomAdapter);
        homeBinding.rcvHome.setLayoutManager(new GridLayoutManager(requireContext(), 2));

        RoomService roomService = RoomServiceApi.getInstance();
        roomService.getRooms().enqueue(new Callback<List<Room>>() {
            @Override
            public void onResponse(Call<List<Room>> call, Response<List<Room>> response) {
                roomAdapter.updateList(response.body());
            }

            @Override
            public void onFailure(Call<List<Room>> call, Throwable t) {
                Log.d(TAG, "" + t);
                Toast.makeText(requireContext(), "Không ổn rồi Đại vương ơi T_T fail", Toast.LENGTH_LONG)
                        .show();
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public void onItemClick(Room room) {
        Navigation.findNavController(requireView()).navigate(HomeFragmentDirections
                .actionNavigationHomeToRoomDetailFragment(room));
    }

}