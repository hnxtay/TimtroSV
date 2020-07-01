package com.dev.kd1412.timtrosv.views;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dev.kd1412.timtrosv.R;
import com.dev.kd1412.timtrosv.databinding.FragmentUploadStep2Binding;

public class UploadFragmentStep2 extends Fragment {
    FragmentUploadStep2Binding uploadStep2Binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        uploadStep2Binding = DataBindingUtil.inflate(inflater,R.layout.fragment_upload_step2,container,false);
        return uploadStep2Binding.getRoot();
    }
}