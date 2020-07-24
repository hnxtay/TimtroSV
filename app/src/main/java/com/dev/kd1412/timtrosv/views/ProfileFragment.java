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
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.dev.kd1412.timtrosv.R;
import com.dev.kd1412.timtrosv.activities.DebugActivity;
import com.dev.kd1412.timtrosv.activities.LoginActivity;
import com.dev.kd1412.timtrosv.databinding.FragmentProfileBinding;
import com.dev.kd1412.timtrosv.model.User;
import com.dev.kd1412.timtrosv.network.RoomServiceApi;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {
    private FragmentProfileBinding profileBinding;
    private User user;
    private FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        profileBinding = DataBindingUtil.inflate(inflater
                , R.layout.fragment_profile, container, false);

        RoomServiceApi.getInstance().getUser(firebaseUser.getUid()).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.body() != null) {
                    user = response.body();
                    profileBinding.tvUsername.setText(user.getmFullName());
                    profileBinding.tvRole.setText(user.getmRole());
                    Log.d("TAG", "onResponse: " + response.body().getmFullName());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });

        Glide.with(requireContext()).load(FirebaseAuth.getInstance().getCurrentUser()
                .getPhotoUrl())
                .circleCrop()
                .into(profileBinding.imgUserAvt);

        profileBinding.btnLogout.setOnClickListener(v -> {
            AuthUI.getInstance().signOut(v.getContext()).addOnCompleteListener(task -> {
                Intent intent = new Intent(requireContext(), LoginActivity.class);
                startActivity(intent);
                requireActivity().finish();
            });
        });

        profileBinding.btnDebug.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), DebugActivity.class);
            startActivity(intent);
        });

        profileBinding.tvRoomAploaded.setOnClickListener(v -> {
            Navigation.findNavController(requireView()).navigate(R.id.action_navigation_profile_to_roomUploadedFragment);
        });

        profileBinding.tvUsername.setOnClickListener(v -> {
            Navigation.findNavController(requireView()).navigate(R.id.action_navigation_profile_to_profileDetailsFragment);
        });
        return profileBinding.getRoot();
    }
}