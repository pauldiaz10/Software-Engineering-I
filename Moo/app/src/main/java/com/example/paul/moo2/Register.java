package com.example.paul.moo2;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Button;
import android.view.View;
import android.support.v7.app.AlertDialog;
import android.content.*;
import java.io.Serializable;
import java.util.*;
import java.util.HashMap;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class Register extends AppCompatActivity{

    private Firebase ref;
    User user;
    Button bRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Firebase.setAndroidContext(this);

        final EditText etAge = (EditText) findViewById(R.id.etAge);
        final EditText etName = (EditText) findViewById(R.id.etName);
        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final EditText etEmail = (EditText) findViewById(R.id.etEmail);
        final EditText etSex = (EditText) findViewById(R.id.etSex);

        bRegister = (Button) findViewById(R.id.bRegister);
        if(bRegister != null){
            bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref = new Firebase("https://mooapp.firebaseio.com/");

                final String name = etName.getText().toString();
                final String username = etUsername.getText().toString();
                final int age = Integer.parseInt(etAge.getText().toString());
                final String password = etPassword.getText().toString();
                final String email = etEmail.getText().toString();
                final String sex = etSex.getText().toString();

                final Firebase usersRef = ref.child("Users");
                user = new User(name, username, password, age, email, sex, 0);

                final Firebase firebaseRef = new Firebase("https://mooapp.firebaseio.com/Users").child(username);
                firebaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(!dataSnapshot.exists()){
                            if(!user.getUserName().equals("")|| !user.getUserName().equals("")|| !user.getPassword().equals("") || !user.getEmail().equals("") || !user.getSex().equals("") || user.getMooPoints() != 0){
                                firebaseRef.setValue(user);
                                Intent registerIntent = new Intent(Register.this, Prof.class);
                                registerIntent.putExtra("Profile", user);
                                Register.this.startActivity(registerIntent);
                            }
                        }else{
                            //another popup and direct
                            Intent registerInten = new Intent(Register.this, testprofileexist.class);
                            registerInten.putExtra("Profile", user);
                            Register.this.startActivity(registerInten);

                        }
                    }
                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });

            }
        });

    }}

}

