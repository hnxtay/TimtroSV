package com.dev.kd1412.timtrosv.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

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

    @SerializedName("room_utilities")
    public List<String> mUtilities;

    @SerializedName("room_description")
    public String mDescription;

    public Room() {
    }

    public Room(int id, String mImg, int mPrice, String mLocation, int mAcreage, List<String> mUtilities, String mDescription) {
        this.id = id;
        this.mImg = mImg;
        this.mPrice = mPrice;
        this.mLocation = mLocation;
        this.mAcreage = mAcreage;
        this.mUtilities = mUtilities;
        this.mDescription = mDescription;
    }

    protected Room(Parcel in) {
        id = in.readInt();
        mImg = in.readString();
        mPrice = in.readInt();
        mLocation = in.readString();
        mAcreage = in.readInt();
        mUtilities = in.createStringArrayList();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(mImg);
        dest.writeInt(mPrice);
        dest.writeString(mLocation);
        dest.writeInt(mAcreage);
        dest.writeStringList(mUtilities);
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
    public static void
    loadImage(ImageView view, String imageUrl) {
        Glide.with(view.getContext())
                .load(imageUrl)
                .into(view);
    }

    @Override
    public String toString() {
        return "Room{" +
                "mImg='" + mImg + '\'' +
                ", mPrice=" + mPrice +
                ", mLocation='" + mLocation + '\'' +
                ", mAcreage=" + mAcreage +
                ", mUtilities=" + mUtilities +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getmImg() {
        return mImg;
    }

    public void setmImg(String mImg) {
        this.mImg = mImg;
    }

    public int getmPrice() {
        return mPrice;
    }

    public void setmPrice(int mPrice) {
        this.mPrice = mPrice;
    }

    public String getmLocation() {
        return mLocation;
    }

    public void setmLocation(String mLocation) {
        this.mLocation = mLocation;
    }

    public int getmAcreage() {
        return mAcreage;
    }

    public void setmAcreage(int mAcreage) {
        this.mAcreage = mAcreage;
    }

    public List<String> getmUtilities() {
        return mUtilities;
    }

    public void setmUtilities(List<String> mUtilities) {
        this.mUtilities = mUtilities;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }
}

