package com.mayer.travelapp.model;

import org.parceler.Parcel;

import java.util.ArrayList;

@Parcel
public class Travel {
    String pushId;
    String index;
    String name;
    String vicinity;
    String mCategory;

    public Travel(){};

    public Travel(String name, String vicinity, String category){
        this.index = "not_specified";
        this.name = name;
        this.vicinity = vicinity;
        this.mCategory = category;

    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVicinity() {
        return vicinity;
    }

    public void setVicinity(String vicinity) {
        this.vicinity = vicinity;
    }

    public String getmCategory() {
        return mCategory;
    }

    public void setmCategory(String mCategory) {
        this.mCategory = mCategory;
    }
}
