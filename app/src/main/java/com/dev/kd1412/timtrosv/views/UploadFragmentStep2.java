package com.dev.kd1412.timtrosv.views;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dev.kd1412.timtrosv.R;
import com.dev.kd1412.timtrosv.databinding.FragmentUploadStep2Binding;
import com.dev.kd1412.timtrosv.model.Room;

public class UploadFragmentStep2 extends Fragment implements View.OnClickListener{
    FragmentUploadStep2Binding uploadStep2Binding;
    private Room room;

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
        uploadStep2Binding = DataBindingUtil.inflate(inflater,R.layout.fragment_upload_step2,container,false);

        uploadStep2Binding.fabBack.setOnClickListener(this);
        uploadStep2Binding.fabNext.setOnClickListener( this);


        return uploadStep2Binding.getRoot();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab_back:
                Navigation.findNavController(requireView()).navigateUp();
                break;
            case R.id.fab_next:
                Navigation.findNavController(requireView()).navigate(R.id.action_uploadFragmentStep2_to_uploadFragmentStep3);
        }
    }
}