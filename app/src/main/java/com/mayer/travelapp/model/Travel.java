package com.mayer.travelapp.model;

import org.parceler.Parcel;

@Parcel
public class Travel {
    String pushId;
    String index;

//    public Travel(){};
//
//    public Travel(){
//        this.index = "not_specified";
//    }

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
}
