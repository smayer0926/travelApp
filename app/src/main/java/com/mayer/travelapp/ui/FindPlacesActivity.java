package com.mayer.travelapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.mayer.travelapp.R;
import com.mayer.travelapp.model.Travel;
import com.mayer.travelapp.service.TravelService;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class FindPlacesActivity extends AppCompatActivity {
    @Bind(R.id.listView) ListView mListView;
    public static final String TAG = FindPlacesActivity.class.getSimpleName();

    public ArrayList<Travel> mTravels = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_places);

        ButterKnife.bind(this);



        Intent intent = getIntent();
            String longitude = intent.getStringExtra("longitude");
            String latitude = intent.getStringExtra("latitude");
            String places = intent.getStringExtra("place");


            String location = longitude + "," + latitude;
        System.out.println(location);
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
                        String [] placesToGo = new String[mTravels.size()];
                        for(int i = 0; i < placesToGo.length; i++){
                            placesToGo[i] = mTravels.get(i).getName();
                        }
                        ArrayAdapter adapter = new ArrayAdapter(FindPlacesActivity.this, android.R.layout.simple_list_item_1, placesToGo);
                        mListView.setAdapter(adapter);

                        for(Travel travel : mTravels) {
                            Log.d(TAG, "Name:" + travel.getName());
                            Log.d(TAG, "Vicinity:" + travel.getVicinity());
                        }
                    }
                });

                try {
                    String jsonData = response.body().string();
                    Log.v(TAG, jsonData);
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        });
    }
}

