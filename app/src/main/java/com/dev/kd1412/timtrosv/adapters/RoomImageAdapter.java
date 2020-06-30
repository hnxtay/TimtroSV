package com.dev.kd1412.timtrosv.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dev.kd1412.timtrosv.R;

import java.util.ArrayList;

public class RoomImageAdapter extends RecyclerView.Adapter<RoomImageAdapter.RoomImageVH> {

    private ArrayList<String> imageList ;

    public RoomImageAdapter(ArrayList<String> imageList) {
        this.imageList = imageList;
    }

    @NonNull
    @Override
    public RoomImageVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.room_image_item,parent,false);
        return new RoomImageVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomImageVH holder, int position) {
        Glide.with(holder.img_room).load(imageList.get(position)).into(holder.img_room);
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public class RoomImageVH extends RecyclerView.ViewHolder {
        private ImageView img_room;
        public RoomImageVH(@NonNull View itemView) {
            super(itemView);
            img_room = (ImageView) itemView.findViewById(R.id.img_room);
        }
    }
}
