package com.example.freeunichat.model;

import com.google.gson.annotations.Expose;

public class Contact {
    private String id;
    private String displayName;
    private String phoneNumber;
    private String avatarImg;

    @Expose
    private byte[] img;

    public Contact(String ID, String displayName, String phoneNumber, String avatarImg){
        this.id = ID;
        this.displayName = displayName;
        this.phoneNumber = phoneNumber;
        this.avatarImg = avatarImg;
        img = null;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getId() {
        return id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img){
        this.img = img;
    }

    public String getAvatarImg() {
        return avatarImg;
    }
}
