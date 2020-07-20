package com.dev.kd1412.timtrosv.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dev.kd1412.timtrosv.R;
import com.dev.kd1412.timtrosv.databinding.RoomImageItemBinding;
import com.google.android.gms.common.util.DataUtils;

import java.util.ArrayList;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageAdapterVH> {
    private ArrayList<String> imageList;

    public ImageAdapter(ArrayList<String> imageList) {
        this.imageList = imageList;
    }

    @NonNull
    @Override
    public ImageAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RoomImageItemBinding imageItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.room_image_item, parent, false);
        return new ImageAdapterVH(imageItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageAdapterVH holder, int position) {
        String imgLink = imageList.get(position);
        Glide.with(holder.imageItemBinding.itemImgRoom).load(imgLink).into(holder.imageItemBinding.itemImgRoom);
    }


    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public class ImageAdapterVH extends RecyclerView.ViewHolder {
        private RoomImageItemBinding imageItemBinding;

        public ImageAdapterVH(@NonNull RoomImageItemBinding imageItemBinding) {
            super(imageItemBinding.getRoot());
            this.imageItemBinding = imageItemBinding;
        }
    }
}
