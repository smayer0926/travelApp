package com.mayer.travelapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.mayer.travelapp.R;

import butterknife.Bind;
import butterknife.ButterKnife;

import static android.R.attr.id;
import static android.R.attr.start;

public class InputActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.submitButton)
    Button mFindPlacesButton;
    @Bind(R.id.longitude)
    EditText mLongitude;
    @Bind(R.id.latitude)
    EditText mLatitude;
    @Bind(R.id.place)
    EditText mPlace;
    @Bind(R.id.savedPlacesButton)
    Button mSavedPlacesButton;
    @Bind(R.id.navigation) BottomNavigationView navigation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        ButterKnife.bind(this);
        mFindPlacesButton.setOnClickListener(this);
        mSavedPlacesButton.setOnClickListener(this);


        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


    }


    @Override
    public void onClick(View v) {
        if (v == mFindPlacesButton) {
            System.out.println();
            String latitude = mLatitude.getText().toString().trim();
            String longitude = mLongitude.getText().toString().trim();
            String place = mPlace.getText().toString().trim();

            Intent intent = new Intent(InputActivity.this, FindPlacesActivity.class);
            intent.putExtra("latitude", latitude);
            intent.putExtra("longitude", longitude);
            intent.putExtra("place", place);
            startActivity(intent);
        } else if (v == mSavedPlacesButton) {
            Intent newIntent = new Intent(InputActivity.this, SavedPlacesActivity.class);
            startActivity(newIntent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.logout, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(InputActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {


        @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_home:
                        Intent homeIntent = new Intent(InputActivity.this, MainActivity.class);
                        startActivity(homeIntent);
                        break;

                    case R.id.menu_save:
                        Intent saveIntent = new Intent(InputActivity.this, SavedPlacesActivity.class);
                        startActivity(saveIntent);
                        break;

                    case R.id.menu_search:
                        Intent searchIntent = new Intent(InputActivity.this, InputActivity.class);
                        startActivity(searchIntent);
                        break;

                }
                return false;
            }

    };
}
