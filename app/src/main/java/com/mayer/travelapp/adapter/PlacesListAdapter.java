package com.mayer.travelapp.adapter;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mayer.travelapp.Constants;
import com.mayer.travelapp.R;
import com.mayer.travelapp.model.Travel;
import com.mayer.travelapp.ui.FindPlacesActivity;
import com.mayer.travelapp.ui.PlacesDetailActivity;
import com.mayer.travelapp.ui.PlacesDetailFragment;

import org.parceler.Parcels;

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

    public class PlacesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Bind(R.id.placesTitle) TextView mPlacesTitle;
//        @Bind(R.id.category) TextView mCategory;
//        @Bind(R.id.vicinity) TextView mVicinity;

        private Context mContext;

        public PlacesViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
        }
        public void bindPlaces(Travel travel) {
            mPlacesTitle.setText(travel.getName());
//            mCategory.setText(travel.getmCategory());
//            mVicinity.setText(travel.getVicinity());
        }
        public void onClick(View v){
            Log.d("click listener", "working");
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, PlacesDetailActivity.class);
            intent.putExtra(Constants.EXTRA_KEY_POSITION, itemPosition +"");
            intent.putExtra(Constants.EXTRA_KEY_POSITION, Parcels.wrap(mTravels));
            mContext.startActivity(intent);

        }
    }
}