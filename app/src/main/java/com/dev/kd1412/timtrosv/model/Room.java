package com.dev.kd1412.timtrosv.model;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Room {

    @SerializedName("id")
    public int id;

    @SerializedName("room_image")
    public String mImg;

    @SerializedName("room_price")
    public int mPrice;

    @SerializedName("room_location")
    public String mLocation;

    @SerializedName("room_acreage")
    public int mAcreage;

    public Room() {
    }

    public Room(int id, String mImg, int mPrice, String mLocation, int mAcreage) {
        this.id = id;
        this.mImg = mImg;
        this.mPrice = mPrice;
        this.mLocation = mLocation;
        this.mAcreage = mAcreage;
    }

    @BindingAdapter("room_image")
    public static void loadImage(ImageView view, String imageUrl) {
        Glide.with(view.getContext())
                .load(imageUrl)
                .into(view);
    }
}

