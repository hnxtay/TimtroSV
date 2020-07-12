package com.dev.kd1412.timtrosv.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.google.gson.annotations.SerializedName;

public class Room implements Parcelable {

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

    protected Room(Parcel in) {
        id = in.readInt();
        mImg = in.readString();
        mPrice = in.readInt();
        mLocation = in.readString();
        mAcreage = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(mImg);
        dest.writeInt(mPrice);
        dest.writeString(mLocation);
        dest.writeInt(mAcreage);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Room> CREATOR = new Creator<Room>() {
        @Override
        public Room createFromParcel(Parcel in) {
            return new Room(in);
        }

        @Override
        public Room[] newArray(int size) {
            return new Room[size];
        }
    };

    @BindingAdapter("room_image")
    public static void loadImage(ImageView view, String imageUrl) {
        Glide.with(view.getContext())
                .load(imageUrl)
                .into(view);
    }
}

