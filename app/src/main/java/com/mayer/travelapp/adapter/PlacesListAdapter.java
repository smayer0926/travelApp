//package com.mayer.travelapp.adapter;
//
//import android.content.Context;
//import android.content.Intent;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import com.mayer.travelapp.Constants;
//import com.mayer.travelapp.R;
//import com.mayer.travelapp.model.Travel;
//import com.mayer.travelapp.ui.InputActivity;
//
//import org.parceler.Parcels;
//
//import java.lang.reflect.Array;
//import java.util.ArrayList;
//
//import butterknife.Bind;
//import butterknife.ButterKnife;
//
//public class PlacesListAdapter extends RecyclerView.Adapter<PlacesListAdapter.PlacesViewHolder> {
//    private ArrayList<Travel> mTravel;
//    private Context mContext;
//
//    public PlacesListAdapter(Context context, ArrayList<Travel> travel){
//        mContext = context;
//        mTravel = travel;
//    }
//    @Override
//    public PlacesListAdapter.PlacesViewHolder onCreateViewHolder(ViewGroup parent, int ViewType){
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.travel_list_item, parent, false);
//        PlacesViewHolder viewHolder = new PlacesViewHolder(view);
//        return viewHolder;
//    }
//
//    @Override
//    public void onBindViewHolder(PlacesListAdapter.PlacesViewHolder holder, int position){
//        holder.bindPlaces(mTravel.get(position));
//
//    }
//    @Override
//    public int getItemCount(){
//        return mTravel.size();
//    }
//    public class PlacesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
//        @Bind(R.id.placesTitle) TextView mTitle;
//
//        private Context mContext;
//
//        public PlacesViewHolder(View itemView){
//            super(itemView);
//            ButterKnife.bind(this, itemView);
//
//            mContext = itemView.getContext();
//            itemView.setOnClickListener(this);
//
//        }
//        public void bindPlaces(Travel travels){
//            mTitle.setText(travels.getName());
//        }
//
//        @Override
//        public void onClick(View v){
//            int itemPosition = getLayoutPosition();
//            Intent intent = new Intent(mContext, InputActivity.class);
//            intent.putExtra(Constants.EXTRA_KEY_POSITION, itemPosition);
//            intent.putExtra(Constants.EXTRA_KEY_TRAVEL, Parcels.wrap(mTravel));
//            mContext.startActivity(intent);
//        }
//    }
//
//}

