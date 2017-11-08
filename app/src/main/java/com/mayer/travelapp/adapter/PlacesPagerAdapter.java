package com.mayer.travelapp.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.mayer.travelapp.model.Travel;
import com.mayer.travelapp.ui.PlacesDetailFragment;

import java.util.ArrayList;

public class PlacesPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Travel> mTravels;


    public PlacesPagerAdapter(FragmentManager fm, ArrayList<Travel> travels){
        super(fm);
        mTravels = travels;
    }
    @Override
    public Fragment getItem(int position){
        return PlacesDetailFragment.newInstance(mTravels, position);
    }
    @Override
    public int getCount(){
        return mTravels.size();
    }
    @Override
    public CharSequence getPageTitle(int position){
        return mTravels.get(position).getName();
    }
}
