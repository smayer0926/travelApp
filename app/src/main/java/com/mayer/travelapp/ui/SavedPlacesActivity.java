package com.mayer.travelapp.ui;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.mayer.travelapp.R;

import butterknife.Bind;
import butterknife.ButterKnife;


public class SavedPlacesActivity extends AppCompatActivity {

    @Bind(R.id.navigation) BottomNavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_places);

        ButterKnife.bind(this);

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {


        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.menu_home:
                    Intent homeIntent = new Intent(SavedPlacesActivity.this, MainActivity.class);
                    startActivity(homeIntent);
                    break;

                case R.id.menu_save:
                    Intent saveIntent = new Intent(SavedPlacesActivity.this, SavedPlacesActivity.class);
                    startActivity(saveIntent);
                    break;

                case R.id.menu_search:
                    Intent searchIntent = new Intent(SavedPlacesActivity.this, InputActivity.class);
                    startActivity(searchIntent);
                    break;

            }
            return false;
        }

    };

}
