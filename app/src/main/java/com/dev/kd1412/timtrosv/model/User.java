/*
 * Copyright (c) 2020.  by kd1412
 */

package com.dev.kd1412.timtrosv.model;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("id")
    private String mId;

    @SerializedName("fullname")
    private String mFullName;

    @SerializedName("role")
    private String mRole;

    @SerializedName("address")
    private String mAddress;

    @SerializedName("email")
    private String mEmail;

    @SerializedName("phone")
    private String mPhone;

    public User() {
    }

    public User(String mId, String mFullName, String mRole, String mAddress, String mEmail, String mPhone) {
        this.mId = mId;
        this.mFullName = mFullName;
        this.mRole = mRole;
        this.mAddress = mAddress;
        this.mEmail = mEmail;
        this.mPhone = mPhone;
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getmFullName() {
        return mFullName;
    }

    public void setmFullName(String mFullName) {
        this.mFullName = mFullName;
    }

    public String getmRole() {
        return mRole;
    }

    public void setmRole(String mRole) {
        this.mRole = mRole;
    }

    public String getmAddress() {
        return mAddress;
    }

    public void setmAddress(String mAddress) {
        this.mAddress = mAddress;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getmPhone() {
        return mPhone;
    }

    public void setmPhone(String mPhone) {
        this.mPhone = mPhone;
    }
}
