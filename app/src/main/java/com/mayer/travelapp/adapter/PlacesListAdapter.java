package com.mayer.travelapp.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mayer.travelapp.R;
import com.mayer.travelapp.model.Travel;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PlacesListAdapter extends RecyclerView.Adapter<PlacesListAdapter.PlacesViewHolder> {
    private ArrayList<Travel> mTravels = new ArrayList<>();
    private Context mContext;

    public PlacesListAdapter(Context context, ArrayList<Travel> travels){
        mContext = context;
        mTravels = travels;
    }
    @Override
    public PlacesListAdapter.PlacesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.travel_list_item, parent, false);
        PlacesViewHolder viewHolder = new PlacesViewHolder(view);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(PlacesListAdapter.PlacesViewHolder holder, int position) {
        holder.bindPlaces(mTravels.get(position));
    }

    @Override
    public int getItemCount() {
        return mTravels.size();
    }

    public class PlacesViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.placesTitle) TextView mPlacesTitle;
        @Bind(R.id.category) TextView mCategory;
        @Bind(R.id.vicinity) TextView mVicinity;

        private Context mContext;

        public PlacesViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }
        public void bindPlaces(Travel travel) {
            mPlacesTitle.setText(travel.getName());
            mCategory.setText(travel.getmCategory());
            mVicinity.setText(travel.getVicinity());
        }
    }



}