package com.dev.kd1412.timtrosv.views;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dev.kd1412.timtrosv.R;
import com.dev.kd1412.timtrosv.databinding.FragmentSearchResultBinding;

public class SearchResultFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentSearchResultBinding resultBinding = DataBindingUtil.inflate(inflater
                ,R.layout.fragment_search_result,container,false);

        return resultBinding.getRoot();
    }
}