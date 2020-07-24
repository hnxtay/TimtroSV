/*
 * Copyright (c) 2020.  by kd1412
 */

package com.dev.kd1412.timtrosv.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.dev.kd1412.timtrosv.BR;
import com.dev.kd1412.timtrosv.R;
import com.dev.kd1412.timtrosv.databinding.RoomItemBinding;
import com.dev.kd1412.timtrosv.model.Room;

import java.util.ArrayList;
import java.util.List;

public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.SearchResultViewHolder> {
    private RoomItemBinding binding;
    private List<Room> roomArrayList;
    private Room room;
    private OnItemClickListener onItemClickListener;

    public SearchResultAdapter(ArrayList<Room> roomArrayList, OnItemClickListener onItemClickListener) {
        this.roomArrayList = roomArrayList;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public SearchResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext())
                , R.layout.room_item, parent, false);
        return new SearchResultViewHolder(binding, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchResultViewHolder holder, int position) {
            room = roomArrayList.get(position);
            holder.binding.setImgURL(room.mImg);
            holder.bind(room);
    }

    @Override
    public int getItemCount() {
        return roomArrayList.size();
    }

    public void updateList(List<Room> rooms) {
        this.roomArrayList = rooms;
        notifyDataSetChanged();
    }

    public class SearchResultViewHolder extends RecyclerView.ViewHolder {
        private RoomItemBinding binding;
        private OnItemClickListener onItemClickListener;

        public SearchResultViewHolder(@NonNull RoomItemBinding binding , OnItemClickListener onItemClickListener) {
            super(binding.getRoot());
            this.binding = binding;
            this.onItemClickListener = onItemClickListener;
        }

        public void bind(Object o) {
            binding.setVariable(BR.room, o);
            binding.getRoot().setOnClickListener(view -> onItemClickListener.onItemClick(room));
            binding.getRoot().setOnCreateContextMenuListener((menu, v, menuInfo) -> {
                menu.add("Sửa");
                menu.add("Xóa");
            });
            binding.hasPendingBindings();
        }
    }
}
