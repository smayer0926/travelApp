package com.mayer.travelapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mayer.travelapp.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class InputActivity extends AppCompatActivity implements View.OnClickListener{
    @Bind(R.id.submitButton) Button mFindPlacesButton;
    @Bind(R.id.longitude) EditText mLongitude;
    @Bind(R.id.latitude) EditText mLatitude;
    @Bind(R.id.place) EditText mPlace;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        ButterKnife.bind(this);
        mFindPlacesButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View v){
        if(v == mFindPlacesButton){
            System.out.println();
            String latitude = mLatitude.getText().toString().trim();
            String longitude = mLongitude.getText().toString().trim();
            String place = mPlace.getText().toString().trim();

            Intent intent = new Intent(InputActivity.this, FindPlacesActivity.class);
            intent.putExtra("latitude", latitude);
            intent.putExtra("longitude", longitude);
            intent.putExtra("place", place);
            startActivity(intent);

        }
    }
}
