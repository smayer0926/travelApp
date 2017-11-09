package com.mayer.travelapp.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.mayer.travelapp.Constants;
import com.mayer.travelapp.R;
import com.mayer.travelapp.adapter.PlacesPagerAdapter;
import com.mayer.travelapp.model.Travel;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PlacesDetailActivity extends AppCompatActivity {
    @Bind(R.id.viewPager) ViewPager mViewPager;
    private PlacesPagerAdapter adapterViewPager;
    ArrayList<Travel> mTravels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places_detail);

        ButterKnife.bind(this);

        mTravels = Parcels.unwrap(getIntent().getParcelableExtra(Constants.EXTRA_KEY_TRAVEL));
        int startingPosition = getIntent().getIntExtra("position", 0);



        adapterViewPager = new PlacesPagerAdapter(getSupportFragmentManager(), mTravels);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
    }


}
