package com.example.paul.moo2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class testprofileexist extends AppCompatActivity {
    private EditText etEmail;
    private User userInfo;
    private Button sendButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testprofileexist);

        etEmail = (EditText) findViewById(R.id.etEmail);
        String email = etEmail.getText().toString();

        Intent intent = getIntent();
        userInfo = (User) intent.getSerializableExtra("profile");

        sendButton = (Button) findViewById(R.id.sendButton);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
