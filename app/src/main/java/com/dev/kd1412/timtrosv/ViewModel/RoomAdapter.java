package com.dev.kd1412.timtrosv.ViewModel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.dev.kd1412.timtrosv.Model.Room;
import com.dev.kd1412.timtrosv.R;
import com.dev.kd1412.timtrosv.databinding.RoomItemBinding;

import java.util.ArrayList;

class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.RoomAdapterViewHolder> {
    private ArrayList<Room> roomArray;

    public RoomAdapter(ArrayList<Room> roomArray) {
        this.roomArray = roomArray;
    }


    @NonNull
    @Override
    public RoomAdapter.RoomAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RoomItemBinding roomItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext())
                ,R.layout.room_item,parent,false);
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RoomAdapter.RoomAdapterViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class RoomAdapterViewHolder extends RecyclerView.ViewHolder{
        public RoomAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
