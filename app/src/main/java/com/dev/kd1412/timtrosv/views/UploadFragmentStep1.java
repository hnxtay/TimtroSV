package com.dev.kd1412.timtrosv.views;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dev.kd1412.timtrosv.R;
import com.dev.kd1412.timtrosv.databinding.FragmentUploadStep1Binding;

public class UploadFragmentStep1 extends Fragment {
    private FragmentUploadStep1Binding uploadStep1Binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        uploadStep1Binding = DataBindingUtil.inflate(inflater,R.layout.fragment_upload_step1,container,false);

        return uploadStep1Binding.getRoot();
    }
}