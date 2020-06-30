package com.dev.kd1412.timtrosv.views;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dev.kd1412.timtrosv.R;
import com.dev.kd1412.timtrosv.databinding.FragmentUploadRoomBinding;


public class UploadRoomFragment extends Fragment {
    private FragmentUploadRoomBinding uploadRoomBinding ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment\
        uploadRoomBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_upload_room,container,false);

        return uploadRoomBinding.getRoot();
    }
}