/*
 * Copyright (c) 2020.  by kd1412
 */

/*
 * Copyright (c) 2020.  by kd1412
 */

package com.dev.kd1412.timtrosv.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dev.kd1412.timtrosv.R;
import com.dev.kd1412.timtrosv.adapters.HomeRoomAdapter;
import com.dev.kd1412.timtrosv.adapters.OnItemClickListener;
import com.dev.kd1412.timtrosv.adapters.SearchResultAdapter;
import com.dev.kd1412.timtrosv.databinding.FragmentRoomUploadedBinding;
import com.dev.kd1412.timtrosv.model.Room;
import com.dev.kd1412.timtrosv.network.RoomServiceApi;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RoomUploadedFragment extends Fragment implements OnItemClickListener {
    private FragmentRoomUploadedBinding uploadedBinding;
    public Room room;
    public ArrayList<Room> roomArrayList;
    private HomeRoomAdapter adapter;
    private int room_id;
    private FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        uploadedBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_room_uploaded, container, false);

        roomArrayList = new ArrayList<>();
        adapter = new HomeRoomAdapter(roomArrayList, this);
        uploadedBinding.rcvRoomUploaded.setHasFixedSize(true);
        uploadedBinding.rcvRoomUploaded.setAdapter(adapter);
        uploadedBinding.rcvRoomUploaded.setLayoutManager(new LinearLayoutManager(requireContext()));

        RoomServiceApi.getInstance().getRoomByUserID(firebaseUser.getUid()).enqueue(new Callback<List<Room>>() {
            @Override
            public void onResponse(Call<List<Room>> call, Response<List<Room>> response) {
                if (response.body() != null && !response.body().get(0).mUserID.toString().equals("")) {
                    adapter.updateList(response.body());
                    Log.d("TAG", "onResponse: " + call.request().url() + "\n"+ response.body().get(3).id);
                    for (int i = 0; i < response.body().size(); i++) {
                        roomArrayList.add(response.body().get(i));
                    }
                    uploadedBinding.tvRoomUploaded.setText("Bài viết đã đăng (" + response.body().size() + ")");
                } else {
                    uploadedBinding.tvRoomUploaded.setText("Bạn chưa đăng tin ");
                }
            }

            @Override
            public void onFailure(Call<List<Room>> call, Throwable t) {

            }
        });
        return uploadedBinding.getRoot();
    }

    @Override
    public void onItemClick(Room room) {
        Navigation.findNavController(requireView()).navigate(RoomUploadedFragmentDirections.actionRoomUploadedFragmentToRoomDetailFragment(room));
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getTitle().toString()) {
            case "Sửa":
                Toast.makeText(requireContext(), "Features are in development process", Toast.LENGTH_SHORT).show();
                break;
            case "Xóa":
                Toast.makeText(requireContext(), "Features are in development process.", Toast.LENGTH_SHORT).show();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + item.getTitle().toString());
        }
        return super.onContextItemSelected(item);
    }

    private void deleteRoom(int roomId) {
        room = roomArrayList.get(roomId);
        Log.d("TAG", "deleteRoom: " + room.id + " " + roomArrayList.get(1).id);
//        RoomServiceApi.getInstance().deleteRoom(room.id).enqueue(new Callback<Room>() {
//            @Override
//            public void onResponse(Call<Room> call, Response<Room> response) {
//                Toast.makeText(requireContext(), "Xoa thanh cong", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onFailure(Call<Room> call, Throwable t) {
//
//            }
//        });
    }
}