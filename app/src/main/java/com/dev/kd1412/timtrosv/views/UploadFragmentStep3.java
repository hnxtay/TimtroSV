package com.dev.kd1412.timtrosv.views;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Image;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dev.kd1412.timtrosv.R;
import com.dev.kd1412.timtrosv.databinding.FragmentUploadStep3Binding;
import com.dev.kd1412.timtrosv.model.Room;
import com.dev.kd1412.timtrosv.network.RoomService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UploadFragmentStep3 extends Fragment {
    private FragmentUploadStep3Binding uploadStep3Binding;
    private static final int REQUEST_CODE = 1;
    private static final String TAG = " adasd";
    RoomService roomService;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requireActivity().getOnBackPressedDispatcher().addCallback(new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Navigation.findNavController(requireView()).navigateUp();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        uploadStep3Binding = DataBindingUtil.inflate(inflater, R.layout.fragment_upload_step3, container, false);

        uploadStep3Binding.fabBack.setOnClickListener(v -> {
            Navigation.findNavController(requireView()).navigateUp();
        });
        uploadStep3Binding.fabNext.setOnClickListener(v -> {
            Navigation.findNavController(requireView()).navigate(R.id.action_uploadFragmentStep3_to_uploadFragmentStep4);
        });

        uploadStep3Binding.btnUpload.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            startActivityForResult(intent, REQUEST_CODE);
        });


        return uploadStep3Binding.getRoot();
    }


}


