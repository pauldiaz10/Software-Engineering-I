package com.example.paul.moo2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.firebase.client.Firebase;

public class Profile extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstancesState){
        super.onCreate(savedInstancesState);
        setContentView(R.layout.activity_profile);
        Firebase.setAndroidContext(this);
    }
}
