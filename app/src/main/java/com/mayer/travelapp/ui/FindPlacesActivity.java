package com.mayer.travelapp.ui;

import android.content.Intent;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;


import com.mayer.travelapp.R;
import com.mayer.travelapp.adapter.PlacesListAdapter;
import com.mayer.travelapp.model.Travel;
import com.mayer.travelapp.service.TravelService;

import org.jsoup.Jsoup;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class FindPlacesActivity extends AppCompatActivity {
    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    private PlacesListAdapter mAdapter;
    public ArrayList<Travel> mTravels = new ArrayList<>();
    @Bind(R.id.navigation)
    BottomNavigationView navigation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_places);

        ButterKnife.bind(this);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        Intent intent = getIntent();
            String longitude = intent.getStringExtra("longitude");
            String latitude = intent.getStringExtra("latitude");
            String places = intent.getStringExtra("place");


            String location = longitude + "," + latitude;

            getPlaces(location, places);

    }


    private void getPlaces(String location, String places){
        final TravelService travelService = new TravelService();
        travelService.findDestinations(location, places, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                mTravels = travelService.processResults(response);
                FindPlacesActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter = new PlacesListAdapter(getApplicationContext(), mTravels);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager =
                                new LinearLayoutManager(FindPlacesActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);
                    }
                });
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {


        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.menu_home:
                    Intent homeIntent = new Intent(FindPlacesActivity.this, MainActivity.class);
                    startActivity(homeIntent);
                    break;

                case R.id.menu_save:
                    Intent saveIntent = new Intent(FindPlacesActivity.this, SavedPlacesActivity.class);
                    startActivity(saveIntent);
                    break;

                case R.id.menu_search:
                    Intent searchIntent = new Intent(FindPlacesActivity.this, InputActivity.class);
                    startActivity(searchIntent);
                    break;

            }
            return false;
        }

    };
}

