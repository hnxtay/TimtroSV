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

import com.bumptech.glide.Glide;
import com.dev.kd1412.timtrosv.R;
import com.dev.kd1412.timtrosv.databinding.FragmentProfileDetailsBinding;
import com.dev.kd1412.timtrosv.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

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

        profileDetailsBinding.edtUsername.setText(firebaseUser.getDisplayName());
        Glide.with(requireContext()).load(firebaseUser.getPhotoUrl()).circleCrop()
                .into(profileDetailsBinding.imgUserAvt);
        profileDetailsBinding.edtEmail.setText(firebaseUser.getEmail());


        return profileDetailsBinding.getRoot();
    }
}