package com.example.paul.moo2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.VideoView;
import android.net.Uri;

import com.firebase.client.Firebase;
import android.widget.MediaController;
public class WatchTrailer extends AppCompatActivity {
    private VideoView videoView;
    private User movie;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_trailer);
        Firebase.setAndroidContext(this);

        Intent intent = getIntent();
        movie = (User) intent.getExtras().getSerializable("Movie");
        user = (User) intent.getExtras().getSerializable("Profile");

        if(movie != null){
            String whichMovie = movie.getFullName();
            if(whichMovie.equals("Barbershop: The Next Cut")){
                //Baber
                startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.youtube.com/watch?v=g2tacfpfpE0")));
            }else if(whichMovie.equals("Captain America: Civil War")){
                //Captain America
                startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.youtube.com/watch?v=dKrVegVI0Us")));
            }else if(whichMovie.equals("Batman v Superman: Dawn of Justice")){
                //Batman
                startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.youtube.com/watch?v=bEyvRQSbR5g")));
            }else if(whichMovie.equals("Keanu")){
                //Keanu
                startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.youtube.com/watch?v=dE0Sw7CvI0k")));
            }else if(whichMovie.equals("Mother's Day")){
                //Mother's Day
                startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.youtube.com/watch?v=2BPr217zLps")));
            }else if(whichMovie.equals("Ratchet & Clank")){
                //Ratchet and Clank
                startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.youtube.com/watch?v=Y1ZYdhBm-Kw")));
            }else if(whichMovie.equals("The Boss")){
                //The Boss
                startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.youtube.com/watch?v=yakeigyf0vc")));
            }else if(whichMovie.equals("The Huntsman: Winter's War")){
                //The huntsman
                startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.youtube.com/watch?v=eAvCgVR0gIM")));
            }else if(whichMovie.equals("The Jungle Book")){
                //The jungle book
                startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.youtube.com/watch?v=5mkm22yO-bs")));
            }else if(whichMovie.equals("Zootopia")){
                //Zootopia
                startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.youtube.com/watch?v=5nP9hU8eUfE")));
            }
        }
    }
}
