package com.dev.kd1412.timtrosv.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.dev.kd1412.timtrosv.BR;
import com.dev.kd1412.timtrosv.model.Room;
import com.dev.kd1412.timtrosv.R;
import com.dev.kd1412.timtrosv.databinding.RoomItemBinding;

import java.util.ArrayList;

public class HomeRoomAdapter extends RecyclerView.Adapter<HomeRoomAdapter.RoomAdapterViewHolder> {
    private ArrayList<Room> roomArray;
    private OnItemClickListener listener;
    private Room room;


    public HomeRoomAdapter(ArrayList<Room> roomArray) {
        this.roomArray = roomArray;
    }


    @NonNull
    @Override
    public HomeRoomAdapter.RoomAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RoomItemBinding roomItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext())
                , R.layout.room_item, parent, false);
        return new RoomAdapterViewHolder(roomItemBinding);
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

    public void updateList(ArrayList<Room> roomArrayList) {
        this.roomArray = roomArrayList;
        notifyDataSetChanged();
    }

    public class RoomAdapterViewHolder extends RecyclerView.ViewHolder {
        public RoomItemBinding itemBinding;

        public RoomAdapterViewHolder(@NonNull RoomItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
        }

        public void bind(Object o) {
            itemBinding.setVariable(BR.room, o);
            itemBinding.hasPendingBindings();
        }
    }
}
