/*
 * Copyright (c) 2020.  by kd1412
 */

package com.dev.kd1412.timtrosv.views;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.dev.kd1412.timtrosv.R;
import com.dev.kd1412.timtrosv.adapters.HomeRoomAdapter;
import com.dev.kd1412.timtrosv.adapters.OnItemClickListener;
import com.dev.kd1412.timtrosv.adapters.SearchResultAdapter;
import com.dev.kd1412.timtrosv.databinding.FragmentSearchBinding;
import com.dev.kd1412.timtrosv.model.Room;
import com.dev.kd1412.timtrosv.network.RoomServiceApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SearchFragment extends Fragment implements OnItemClickListener {
    private FragmentSearchBinding fragmentSearchBinding;
    private Room room;
    private HomeRoomAdapter roomAdapter;
    private ArrayList<Room> roomArrayList;
    private SearchView searchView = null;
    private SearchView.OnQueryTextListener queryTextListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentSearchBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container
                , false);

        roomArrayList = new ArrayList<>();
        roomAdapter = new HomeRoomAdapter(roomArrayList, this);
        fragmentSearchBinding.rcvResult.setAdapter(roomAdapter);
        fragmentSearchBinding.rcvResult.setHasFixedSize(true);
        fragmentSearchBinding.rcvResult.setLayoutManager(new LinearLayoutManager(requireContext()));

        return fragmentSearchBinding.getRoot();
    }

    @Override
    public void onItemClick(Room room) {
        Navigation.findNavController(requireView()).navigate(SearchFragmentDirections.actionNavigationSearchToRoomDetailFragment(room));
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.toolbar_more, menu);
        MenuItem item = menu.findItem(R.id.search);
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        if (item != null){
            searchView = (SearchView) item.getActionView();
            Log.d("TAG", "onCreateOptionsMenu: " );
        }
        if (searchView != null){
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
            Log.d("TAG", "onCreateOptionsMenu: ");
            queryTextListener = new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    Log.i("TAG", "onQueryTextSubmit: " + query);
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    Log.i("TAG", "onQueryTextChange: " + newText);
                    return true;
                }
            };
        }
        super.onCreateOptionsMenu(menu, inflater);
    }
}