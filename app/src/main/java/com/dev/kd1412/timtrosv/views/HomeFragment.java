package com.dev.kd1412.timtrosv.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dev.kd1412.timtrosv.model.Room;
import com.dev.kd1412.timtrosv.R;
import com.dev.kd1412.timtrosv.model.Rooms;
import com.dev.kd1412.timtrosv.network.RoomService;
import com.dev.kd1412.timtrosv.network.RoomServiceApi;
import com.dev.kd1412.timtrosv.adapters.HomeRoomAdapter;
import com.dev.kd1412.timtrosv.databinding.FragmentHomeBinding;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {
    public ArrayList<Room> roomArrayList;
    private Room room;
    public HomeRoomAdapter roomAdapter;
    private static final String TAG = "HomeFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        FragmentHomeBinding homeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);

        roomArrayList = new ArrayList();

        roomAdapter = new HomeRoomAdapter(roomArrayList);
        homeBinding.rcvHome.setAdapter(roomAdapter);
        homeBinding.rcvHome.setLayoutManager(new GridLayoutManager(requireContext(), 2));

        RoomService roomService = RoomServiceApi.getInstance();
        roomService.getRooms().enqueue(new Callback<Rooms>() {
            @Override
            public void onResponse(Call<Rooms> call, Response<Rooms> response) {

                for (Room room : response.body().getRooms()){
                    room = new Room(room.id,room.mImg,room.mPrice,room.mLocation,room.mAcreage);
                    roomArrayList.add(room);
                    roomAdapter.updateList(roomArrayList);
                }
            }

            @Override
            public void onFailure(Call<Rooms> call, Throwable t) {
                Log.d(TAG,""+t);
            }
        });


        return homeBinding.getRoot();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }
}