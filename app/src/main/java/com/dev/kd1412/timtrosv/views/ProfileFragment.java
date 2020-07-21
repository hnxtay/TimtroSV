package com.dev.kd1412.timtrosv.views;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.dev.kd1412.timtrosv.R;
import com.dev.kd1412.timtrosv.activities.DebugActivity;
import com.dev.kd1412.timtrosv.activities.LoginActivity;
import com.dev.kd1412.timtrosv.databinding.FragmentProfileBinding;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ProfileFragment extends Fragment {
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentProfileBinding profileBinding = DataBindingUtil.inflate(inflater
                ,R.layout.fragment_profile,container,false);

        profileBinding.tvUsername.setText(FirebaseAuth.getInstance().getCurrentUser().getDisplayName());
        Glide.with(requireContext()).load(FirebaseAuth.getInstance().getCurrentUser().getPhotoUrl())
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
        return profileBinding.getRoot();
    }
}