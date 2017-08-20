package com.example.paul.moo2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import android.view.View.OnClickListener;
import com.firebase.client.ValueEventListener;
import com.firebase.client.*;
import java.util.*;

public class MainActivity extends AppCompatActivity {
    private static Firebase ref;
    private Button bLogin;
    private EditText etUsername;
    private EditText etPassword;
    private PopupWindow popupWindow;
    private LayoutInflater layoutInflater;
    private RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);

        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        relativeLayout = (RelativeLayout) findViewById(R.id.relative);
        ref = new Firebase("https://mooapp.firebaseio.com/");

        final TextView tvRegisterLink = (TextView) findViewById(R.id.tvRegisterLink);
        if (tvRegisterLink != null) {
            tvRegisterLink.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent registerIntent = new Intent(MainActivity.this, Register.class);
                    MainActivity.this.startActivity(registerIntent);
                }
            });
        }

        bLogin = (Button) findViewById(R.id.bLogin);
        if (bLogin != null) {
            bLogin.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                final String username = etUsername.getText().toString();
                final String pass = etPassword.getText().toString();

                final Firebase firebaseRef = new Firebase("https://mooapp.firebaseio.com/Users").child(username);
                firebaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()){
                            Firebase ref = new Firebase("https://mooapp.firebaseio.com/Users");

                            ref.addValueEventListener(new ValueEventListener() {
                                @Override

                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    boolean found = false;
                                    String userNameFire = "";
                                    String passwordFire = "";
                                    String nameFire = "";
                                    String emailFire = "";
                                    String sexFire = "";
                                    Long ageFire;
                                    Long moopointsFire;
                                    User userMain = new User();

                                    for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                                        moopointsFire = (Long) snapshot.child("mooPoints").getValue();
                                        int moopointsFireInt = moopointsFire.intValue();

                                        sexFire = (String) snapshot.child("sex").getValue();

                                        ageFire = (Long) snapshot.child("age").getValue();
                                        int ageFireInt = ageFire.intValue();

                                        nameFire = (String) snapshot.child("fullName").getValue();
                                        emailFire = (String) snapshot.child("email").getValue();
                                        userNameFire = (String) snapshot.child("userName").getValue();
                                        passwordFire = (String) snapshot.child("password").getValue();

                                        if(userNameFire.equals(username) && passwordFire.equals(pass)){
                                            userMain.setAge(ageFireInt);
                                            userMain.setMooPoints(moopointsFireInt);
                                            userMain.setSex(sexFire);
                                            userMain.setFullName(nameFire);
                                            userMain.setEmail(emailFire);
                                            userMain.setUserName(userNameFire);
                                            userMain.setPassword(passwordFire);
                                            //userMain = new User(nameFire, userNameFire, passwordFire, ageFireInt, emailFire, sexFire, moopointsFireInt);
                                        }

                                    }//end for loop
                                    //Log.d("", userMain.getFullName() + " " + userMain.getPassword());
                                    if(userMain.getUserName().equals(username) && userMain.getPassword().equals(pass) && !username.equals("")){
                                        Intent registerIntent = new Intent(MainActivity.this, Prof.class);
                                        registerIntent.putExtra("Profile", userMain);
                                        MainActivity.this.startActivity(registerIntent);

                                    }else{
                                        if(username.equals("") || pass.equals("")){
                                            startActivity(new Intent(MainActivity.this, testNoInputEntered.class));
                                        }else{
                                            startActivity(new Intent(MainActivity.this, testpopup.class));
                                        }
                                    }
                                }

                                @Override
                                public void onCancelled(FirebaseError firebaseError) {

                                }
                            });

                        }else{
                            startActivity(new Intent(MainActivity.this, testwrongpass.class));
                        }
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });
                }

            });
        }
    }//end onCreate()
}//end

