package com.example.paul.moo2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.client.Firebase;

import java.util.*;

public class test extends AppCompatActivity {
    private TextView text;
    private String titles;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Firebase.setAndroidContext(this);

        text = (TextView) findViewById(R.id.test);

        Intent intent = getIntent();
        titles =  (String) intent.getSerializableExtra("test");

        text.setText(titles);
    }
}
