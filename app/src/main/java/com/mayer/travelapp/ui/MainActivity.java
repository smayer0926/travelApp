package com.mayer.travelapp.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mayer.travelapp.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.signup) Button mSignUp;
    @Bind(R.id.login) Button mLogin;
    @Bind(R.id.main_text) TextView mTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mSignUp.setOnClickListener(this);
        mLogin.setOnClickListener(this);
    }



    @Override
    public void onClick(View v){
        if(v == mSignUp){
            Intent signupIntent = new Intent(MainActivity.this, InputActivity.class);
            startActivity(signupIntent);
        }
    }
//        if(v == mSignUp){
//            Intent signupIntent = new Intent(MainActivity.this, InputActivity.class);
//            startActivity(signupIntent);
//        } else if(v == mLogin){
//            Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
//            startActivity(loginIntent);
//        }else {
//            Toast.makeText(this, "There was an error, please try again", Toast.LENGTH_SHORT).show();
//            Intent errorIntent = new Intent(MainActivity.this, MainActivity.class);
//            startActivity(errorIntent);
//        }
//    }
}
