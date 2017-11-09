package com.mayer.travelapp.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mayer.travelapp.R;
import com.mayer.travelapp.model.Travel;

public class FirebaseTravelViewHolder extends RecyclerView.ViewHolder{
    View mView;
    Context mContext;
    public TextView mTravelTextView;

    public FirebaseTravelViewHolder(View itemView){
        super(itemView);
        mView = itemView;
        mContext= itemView.getContext();

    }
    public void bindPlaces(Travel travel){
        mTravelTextView = (TextView) mView.findViewById(R.id.placesTitle);
        mTravelTextView.setText(travel.getName());
    }
}
