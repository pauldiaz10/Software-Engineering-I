package com.example.paul.moo2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.client.Firebase;

import org.w3c.dom.Comment;
import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class showmovie extends AppCompatActivity implements Serializable {

    private User movie;
    private User user;

    private Button watchTrailer;
    private Button browseButton;
    private Button pointsButton;
    private Button commentsButton;

    private TextView titleView;
    private TextView castView;
    private TextView synopsisView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showmovie);
        Firebase.setAndroidContext(this);

        Intent intent = getIntent();
        movie = (User) intent.getExtras().getSerializable("Movie");
        user = (User) intent.getExtras().getSerializable("Profile");

        watchTrailer = (Button) findViewById(R.id.watchTrailer);
        watchTrailer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent registerIntent = new Intent(showmovie.this, WatchTrailer.class);
                registerIntent.putExtra("Movie", movie);
                registerIntent.putExtra("Profile", user);
                showmovie.this.startActivity(registerIntent);
            }
        });

        commentsButton = (Button) findViewById(R.id.commentsButton);
        commentsButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(movie != null && user != null){
                Intent registerIntent = new Intent(showmovie.this, Comments.class);
                registerIntent.putExtra("Movie", movie);
                registerIntent.putExtra("Profile", user);
                showmovie.this.startActivity(registerIntent);
                }
            }
        });

        browseButton = (Button) findViewById(R.id.browseButton);
        browseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(showmovie.this, BrowseMovie.class);
                registerIntent.putExtra("Movie", movie);
                registerIntent.putExtra("Profile", user);
                showmovie.this.startActivity(registerIntent);
            }
        });

        //IMAGE SET
        int temp = 0;
        if(movie != null) {
            temp = movie.getAge();

            List<String> posterUrl = movie.getPostr();
            String image = posterUrl.get(temp);

            int id = getResources().getIdentifier(image, "drawable", getPackageName());
            ImageView imageView = (ImageView) findViewById(R.id.imageMovie);
            imageView.setImageResource(id);
        }
        //Moo points
        pointsButton = (Button) findViewById(R.id.etMooPoints);
        if(user != null){
            pointsButton.setText("Points: " + user.getMooPoints());
        }

        //MOVIE TITLE
        String movieTitle = "";
        if(movie != null){
            movieTitle = movie.getFullName();
        }
        titleView = (TextView) findViewById(R.id.etTitle);
        if(user != null){
            titleView.setText(movieTitle);
        }

        //MOVIE CAST
        String message = "";
        List<ArrayList<String>> cast = new ArrayList<>();
        if(movie != null){
            cast = movie.getCast();
            message += "CAST:\n";
            for(int i = 0; i < movie.getCast().size(); i++){
                message += cast.get(movie.getAge()).get(i) +" ";
            }
            castView = (TextView) findViewById(R.id.etCast);
            castView.setText(message);
        }


        //MOVIE SYNOPSIS
        String synopsis = "SYNOPSIS:\n";
        if(movie != null){
            synopsis += movie.getUserName();
            synopsisView = (TextView) findViewById(R.id.etSynopsis);
            synopsisView.setText(synopsis);
        }
    }
}
