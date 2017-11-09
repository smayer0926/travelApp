package com.mayer.travelapp.adapter;


import android.content.Context;
import android.content.Intent;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.mayer.travelapp.Constants;


import com.mayer.travelapp.model.Travel;
import com.mayer.travelapp.ui.PlacesDetailActivity;
import com.mayer.travelapp.util.ItemTouchHelperAdapter;
import com.mayer.travelapp.util.OnStartDragListener;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.Collections;

public class FirebaseTravelListAdapter extends FirebaseRecyclerAdapter<Travel, FirebaseTravelViewHolder> implements ItemTouchHelperAdapter {
    private DatabaseReference mRef;
    private OnStartDragListener mOnStartDragListener;
    private Context mContext;
    private ChildEventListener mChildEventListener;
    private ArrayList<Travel> mTravels = new ArrayList<>();
    private int mOrientation;



    public FirebaseTravelListAdapter(Class<Travel> modelClass, int modelLayout, Class<FirebaseTravelViewHolder> viewHolderClass,
                                     Query ref, OnStartDragListener onStartDragListener, Context context){
        super(modelClass, modelLayout, viewHolderClass, ref);
        mRef = ref.getRef();
        mOnStartDragListener = onStartDragListener;
        mContext = context;

        mChildEventListener = mRef.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                mTravels.add(dataSnapshot.getValue(Travel.class));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void populateViewHolder(final FirebaseTravelViewHolder viewHolder, Travel model
            , int position){
        viewHolder.bindPlaces(model);
        mOrientation = viewHolder.itemView.getResources().getConfiguration().orientation;

//        if(mOrientation == Configuration.ORIENTATION_LANDSCAPE){
//            createDetailFragment(0);
//        }

        viewHolder.mTravelTextView.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event){
                if(MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN){
                    mOnStartDragListener.onStartDrag(viewHolder);
                }
                return false;
            }
        });

        viewHolder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int itemPosition = viewHolder.getAdapterPosition();
//                if (mOrientation == Configuration.ORIENTATION_LANDSCAPE) {
//                    createDetailFragment(itemPosition);
//                if {
                    Intent intent = new Intent(mContext, PlacesDetailActivity.class);
                    intent.putExtra(Constants.EXTRA_KEY_POSITION, itemPosition);
                    intent.putExtra(Constants.EXTRA_KEY_TRAVEL, Parcels.wrap(mTravels));
                    mContext.startActivity(intent);
//                }
            }
        });
    }

//    private void createDetailFragment(int position){
//        PlacesDetailFragment detailFragment = PlacesDetailFragment.newInstance(mTravels, position);
//        FragmentTransaction ft = ((FragmentActivity) mContext).getSupportFragmentManager().beginTransaction();
//        ft.replace(R.id.travelDetailContainer, detailFragment);
//        ft.commit();
//    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition){
        Collections.swap(mTravels, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return false;
    }
    @Override
    public void onItemDismiss(int position){
        mTravels.remove(position);
        getRef(position).removeValue();

    }

    private void setIndexInFirebase(){
        for(Travel travel: mTravels){
            int index = mTravels.indexOf(travel);
            DatabaseReference ref = getRef(index);
            travel.setIndex(Integer.toString(index));
            ref.setValue(travel);
        }
    }

    @Override
    public void cleanup(){
        super.cleanup();
        setIndexInFirebase();
        mRef.removeEventListener(mChildEventListener);
    }


}

