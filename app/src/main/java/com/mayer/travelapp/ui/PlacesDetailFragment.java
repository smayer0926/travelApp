package com.mayer.travelapp.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mayer.travelapp.Constants;
import com.mayer.travelapp.R;
import com.mayer.travelapp.model.Travel;

import org.jsoup.Jsoup;
import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


public class PlacesDetailFragment extends Fragment implements View.OnClickListener {
    @Bind(R.id.vicinity) TextView mVicinity;
    @Bind(R.id.category) TextView mCategory;
    @Bind(R.id.placesTitle) TextView mTitle;
    @Bind(R.id.saveButton)
    Button mSaveButton;

    private Travel mTravel;
    private int mPosition;
    private ArrayList<Travel> mTravels;

    public static PlacesDetailFragment newInstance(ArrayList<Travel> travels, Integer position){
        PlacesDetailFragment placesDetailFragment = new PlacesDetailFragment();
        Bundle args = new Bundle();

        args.putParcelable(Constants.EXTRA_KEY_TRAVEL, Parcels.wrap(travels));
        args.putInt(Constants.EXTRA_KEY_POSITION, position);

        placesDetailFragment.setArguments(args);
        return placesDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mTravels = Parcels.unwrap(getArguments().getParcelable(Constants.EXTRA_KEY_TRAVEL));
        mPosition = getArguments().getInt(Constants.EXTRA_KEY_POSITION);
        mTravel = mTravels.get(mPosition);



    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_places_detail, container,false);
        ButterKnife.bind(this, view);

        mTitle.setText(mTravel.getName());
        mCategory.setText( mTravel.getmCategory());
        String description = (mTravel.getVicinity());


        String revisedDescription = html2text(description);

        mVicinity.setText(revisedDescription);

        mSaveButton.setOnClickListener(this);

        return view;
    }
    @Override
    public void onClick(View v){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        DatabaseReference travelRef = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_TRAVEL_SAVED)
                .child(uid);
        DatabaseReference pushRef = travelRef.push();
        String pushId = pushRef.getKey();
        mTravel.setPushId(pushId);
        pushRef.setValue(mTravel);
        Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
    }


    public static String html2text(String html){
        return Jsoup.parse(html).text();
    }
}
