package com.example.paul.moo2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.firebase.client.Firebase;

import android.view.View;
import android.widget.*;
import java.io.Serializable;
import android.content.Intent;

public class Prof extends AppCompatActivity implements Serializable{

    private User userInfo;
    private Button menu;
    private Firebase ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prof);
        Firebase.setAndroidContext(this);

        Intent intent = getIntent();
        userInfo = (User) intent.getSerializableExtra("Profile");

        String name = " ";
        if(userInfo != null){
        if(userInfo.getFullName() != null){
            name =  userInfo.getFullName();
        }

        TextView textName = (TextView) findViewById(R.id.etName);
        textName.setText(name);
        textName.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

            }
        });

        TextView textEmail = (TextView) findViewById(R.id.etEmail);
        textEmail.setText(userInfo.getEmail());

        TextView textUsername = (TextView) findViewById(R.id.etUsername);
        textUsername.setText(userInfo.getUserName());

        TextView textMooPoints = (TextView) findViewById(R.id.etMooPoints);
        int points = userInfo.getMooPoints();
        String strPoints = Integer.toString(points);
        textMooPoints.setText(strPoints);
        }

        //This will direct user to main page which is browseMovies
        menu = (Button) findViewById(R.id.etMenu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(Prof.this, BrowseMovie.class);
                registerIntent.putExtra("Profile", userInfo);
                //registerIntent.putExtra("Movie", )
                Prof.this.startActivity(registerIntent);
            }
        });

    }
}
