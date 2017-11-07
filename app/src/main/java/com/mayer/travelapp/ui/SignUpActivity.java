package com.mayer.travelapp.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.mayer.travelapp.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private ProgressDialog mAuthProgressDialog;
    private String mExtraName;

    @Bind(R.id.sign_up_Button) Button mSignUpButton;
    @Bind(R.id.Email)EditText mEmail;
    @Bind(R.id.password) EditText mPassword;
    @Bind(R.id.confirmPasswordEditText) EditText mConfirmPassword;
    @Bind(R.id.name) EditText mName;
    @Bind(R.id.loginTextView)
    TextView mLoginTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();

//        createAuthStateListener();
        createAuthProgressDialog();


        mSignUpButton.setOnClickListener(this);
        mLoginTextView.setOnClickListener(this);

    }
    @Override
    public void onStart(){
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
    @Override
    public void onStop(){
        super.onStop();
        if(mAuthListener != null){
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
    @Override
    public void onClick(View view){
        if(view == mLoginTextView){
            Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
        if(view == mSignUpButton){
            createNewUser();
        }

    }
    private void createNewUser(){
        mExtraName = mName.getText().toString().trim();
        String name = mName.getText().toString().trim();
        final String email = mEmail.getText().toString().trim();
        String password = mPassword.getText().toString().trim();
        String confirmPassword = mConfirmPassword.getText().toString().trim();

        boolean validEmail = isValidEmail(email);
        boolean validName = isValidName(mExtraName);
        boolean validPassword = isValidPassword(password,confirmPassword);

        if(!validEmail || !validName || !validPassword) return;
        mAuthProgressDialog.show();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        mAuthProgressDialog.dismiss();

                        if(task.isSuccessful()){
                            createFirebaseUserProfile(task.getResult().getUser());
//                            sendToWelcome();
                        }
                        else{
                            Toast.makeText(SignUpActivity.this, "Authentication Failed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    private void createFirebaseUserProfile(final FirebaseUser user){
        UserProfileChangeRequest addProfileName = new UserProfileChangeRequest.Builder()
                .setDisplayName(mExtraName)
                .build();
        user.updateProfile(addProfileName)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                        }
                    }
                });
    }
//    private void createAuthStateListener(){
//        mAuthListener = new FirebaseAuth.AuthStateListener(){
//            @Override
//            public void onAuthStatehanged(@NonNull FirebaseAuth firebaseAuth){
//                final FirebaseUser user = firebaseAuth.getCurrentUser();
//                if(user != null){
//                    Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                    startActivity(intent);
//                    finish();
//                }
//            }
//        };
//    }
    private void createAuthProgressDialog(){
        mAuthProgressDialog = new ProgressDialog(this);
        mAuthProgressDialog.setTitle("Loading...");
        mAuthProgressDialog.setMessage("Authenticating with Firebase...");
        mAuthProgressDialog.setCancelable(false);
    }
    private boolean isValidEmail(String email){
        boolean isGoodEmail = (email != null && Patterns.EMAIL_ADDRESS.matcher(email).matches());
        if(!isGoodEmail){
            mEmail.setError("Please enter a valid email address");
            return false;
        }
        return isGoodEmail;
    }
    private boolean isValidName(String name){
        if(name.equals("")){
            mName.setError("Please enter your name");
            return false;
        }
        return true;
    }
    private boolean isValidPassword(String password, String confirmPassword){
        if(password.length() < 6){
            mPassword.setError("Please create a password containing at least 6 characters");
            return false;
        }
        else if(!password.equals(confirmPassword)){
            mPassword.setError("Passwords do not match");
            return false;
        }
        return true;
    }


}