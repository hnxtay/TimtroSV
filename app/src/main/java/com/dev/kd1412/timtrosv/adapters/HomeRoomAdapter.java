package com.dev.kd1412.timtrosv.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.dev.kd1412.timtrosv.BR;
import com.dev.kd1412.timtrosv.model.Room;
import com.dev.kd1412.timtrosv.R;
import com.dev.kd1412.timtrosv.databinding.RoomItemBinding;

import java.util.ArrayList;
import java.util.List;

public class HomeRoomAdapter extends RecyclerView.Adapter<HomeRoomAdapter.RoomAdapterViewHolder> {
    private List<Room> roomArray;
    private OnItemClickListener listener;
    private Room room;
    OnItemClickListener itemClickListener;


    public HomeRoomAdapter(ArrayList<Room> roomArray, OnItemClickListener itemClickListener) {
        this.roomArray = roomArray;
        this.itemClickListener = itemClickListener;
    }




    @NonNull
    @Override
    public HomeRoomAdapter.RoomAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RoomItemBinding roomItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext())
                , R.layout.room_item, parent, false);
        return new RoomAdapterViewHolder(roomItemBinding, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeRoomAdapter.RoomAdapterViewHolder holder, int position) {
        room = roomArray.get(position);
        holder.itemBinding.setImgURL(room.mImg);
        holder.bind(room);
    }

    @Override
    public int getItemCount() {
        return roomArray.size();
    }

    public void updateList(List<Room> roomArrayList) {
        this.roomArray = roomArrayList;
        notifyDataSetChanged();
    }

    public class RoomAdapterViewHolder extends RecyclerView.ViewHolder {
        public RoomItemBinding itemBinding;
        OnItemClickListener onClickListener;

        public RoomAdapterViewHolder(@NonNull RoomItemBinding itemBinding, OnItemClickListener onClickListener) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
            this.onClickListener = onClickListener;
        }

        public void bind(Room room) {
            itemBinding.setRoom(room);
            itemBinding.getRoot().setOnClickListener(view -> onClickListener.onItemClick(room));
            itemBinding.hasPendingBindings();
        }
    }
}
