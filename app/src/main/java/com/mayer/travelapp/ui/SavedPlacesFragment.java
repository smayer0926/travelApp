package com.mayer.travelapp.ui;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.mayer.travelapp.Constants;
import com.mayer.travelapp.R;
import com.mayer.travelapp.adapter.FirebaseTravelListAdapter;
import com.mayer.travelapp.adapter.FirebaseTravelViewHolder;
import com.mayer.travelapp.model.Travel;
import com.mayer.travelapp.util.OnStartDragListener;
import com.mayer.travelapp.util.SimpleItemTouchHelperCallback;

import butterknife.Bind;
import butterknife.ButterKnife;


public class SavedPlacesFragment extends Fragment implements OnStartDragListener {
    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    private FirebaseTravelListAdapter mFirebaseAdapter;
    private ItemTouchHelper mItemTouchHelper;

    public SavedPlacesFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_saved_places_list, container, false);
        ButterKnife.bind(this, view);
        setUpFirebaseAdapter();
        return view;
    }
    private void setUpFirebaseAdapter(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        Query query = FirebaseDatabase.getInstance()
                .getReference(Constants.FIREBASE_CHILD_TRAVEL_SAVED)
                .child(uid)
                .orderByChild(Constants.FIREBASE_QUERY_INDEX);

        mFirebaseAdapter = new FirebaseTravelListAdapter(Travel.class, R.layout.travel_list_item, FirebaseTravelViewHolder.class, query, this, getActivity());

        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mFirebaseAdapter);

        mFirebaseAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver(){
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount){
                super.onItemRangeInserted(positionStart, itemCount);
                mFirebaseAdapter.notifyDataSetChanged();
            }
        });
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mFirebaseAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);

    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }
}
