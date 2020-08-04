/*
 * Copyright (c) 2020.  by kd1412
 */

package com.dev.kd1412.timtrosv.views;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.Toast;

import com.dev.kd1412.timtrosv.R;
import com.dev.kd1412.timtrosv.adapters.HomeRoomAdapter;
import com.dev.kd1412.timtrosv.adapters.OnItemClickListener;
import com.dev.kd1412.timtrosv.adapters.SearchResultAdapter;
import com.dev.kd1412.timtrosv.databinding.FragmentSearchBinding;
import com.dev.kd1412.timtrosv.model.Room;
import com.dev.kd1412.timtrosv.network.RoomServiceApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SearchFragment extends Fragment implements OnItemClickListener {
    private FragmentSearchBinding fragmentSearchBinding;
    private Room room;
    private HomeRoomAdapter roomAdapter;
    private ArrayList<Room> roomArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentSearchBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container
                , false);

        roomArrayList = new ArrayList<>();
        roomAdapter = new HomeRoomAdapter(roomArrayList, this);
        fragmentSearchBinding.rcvResult.setAdapter(roomAdapter);
        fragmentSearchBinding.rcvResult.setHasFixedSize(true);
        fragmentSearchBinding.rcvResult.setLayoutManager(new LinearLayoutManager(requireContext()));

        fragmentSearchBinding.edtSearch.setOnEditorActionListener((v, actionId, event) -> {
            if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER))
                    || (actionId == EditorInfo.IME_ACTION_DONE)) {
                String location = fragmentSearchBinding.edtSearch.getText().toString();
                if (location.isEmpty()) {
                    Toast.makeText(requireContext(), "Vui lòng nhập thông tin tìm kiếm.",
                            Toast.LENGTH_SHORT).show();
                } else {
                    RoomServiceApi.getInstance().getRoomLocation(location).enqueue(new Callback<List<Room>>() {
                        @Override
                        public void onResponse(Call<List<Room>> call, Response<List<Room>> response) {
                            if (response.body() != null) {
                                roomAdapter.updateList(response.body());
                                fragmentSearchBinding.tvResult.setText("Kết quả tìm được(" + response.body().size() + ")");
                            } else {
                                fragmentSearchBinding.tvResult.setText("Không có kết quả phù hợp");
                            }
                        }

                        @Override
                        public void onFailure(Call<List<Room>> call, Throwable t) {
                            Log.d("TAG", "" + t);
                            Toast toast = Toast.makeText(requireContext(), "Không có kết quả cần tìm T_T", Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.CENTER, 0, 0);
                            toast.show();
                        }
                    });
                }

            }
            return false;
        });


        return fragmentSearchBinding.getRoot();
    }


    @Override
    public void onItemClick(Room room) {
        Navigation.findNavController(requireView()).navigate(SearchFragmentDirections.actionNavigationSearchToRoomDetailFragment(room));
    }
}