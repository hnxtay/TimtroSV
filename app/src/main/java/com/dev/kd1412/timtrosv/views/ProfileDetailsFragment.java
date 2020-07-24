/*
 * Copyright (c) 2020.  by kd1412
 */

/*
 * Copyright (c) 2020.  by kd1412
 */

package com.dev.kd1412.timtrosv.views;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dev.kd1412.timtrosv.R;
import com.dev.kd1412.timtrosv.databinding.FragmentProfileDetailsBinding;
import com.dev.kd1412.timtrosv.model.User;
import com.dev.kd1412.timtrosv.network.RoomServiceApi;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileDetailsFragment extends Fragment {
    private FragmentProfileDetailsBinding profileDetailsBinding;
    private User user;
    private FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        profileDetailsBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_profile_details
                ,container,false);

        RoomServiceApi.getInstance().getUser(firebaseUser.getUid()).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.body() != null){
                    user = response.body();
                    profileDetailsBinding.edtEmail.setText(user.getmEmail());
                    profileDetailsBinding.edtPhone.setText(user.getmPhone());
                    profileDetailsBinding.edtAddress.setText(user.getmAddress());
                    profileDetailsBinding.edtRole.setText(user.getmRole());
                    profileDetailsBinding.edtUsername.setText(firebaseUser.getDisplayName());
                    Glide.with(requireContext()).load(firebaseUser.getPhotoUrl()).circleCrop()
                            .into(profileDetailsBinding.imgUserAvt);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
        profileDetailsBinding.btnUpdate.setOnClickListener(v -> {
            getUserUpdateValues();
            Toast.makeText(requireContext(), "Features are in development process", Toast.LENGTH_SHORT).show();
        });

        return profileDetailsBinding.getRoot();
    }

    private void getUserUpdateValues() {

    }
}