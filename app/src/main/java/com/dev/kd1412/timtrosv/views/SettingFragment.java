/*
 * Copyright (c) 2020.  by kd1412
 */

package com.dev.kd1412.timtrosv.views;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dev.kd1412.timtrosv.R;
import com.dev.kd1412.timtrosv.databinding.FragmentSettingBinding;


public class SettingFragment extends Fragment {
    private FragmentSettingBinding settingBinding;
    private final String lightMode = "light";
    private final String darkMode = "dark";
    private final String defaultMode = "default";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        settingBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_setting,container,false);

        Toast.makeText(requireContext(), "Features are in development process", Toast.LENGTH_SHORT).show();
//        switch (getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK) {
//            case Configuration.UI_MODE_NIGHT_YES:
//                Log.d("TAG", "onCreateView: Dark mode" );
//                break;
//            case Configuration.UI_MODE_NIGHT_NO:
//                Log.d("TAG", "onCreateView: Light mode" );
//                break;
//        }
//        settingBinding.btnLightmode.setOnClickListener(v -> {
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//        });
//        settingBinding.btnDarkmode.setOnClickListener(v -> {
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//        });
        return settingBinding.getRoot();
    }
}