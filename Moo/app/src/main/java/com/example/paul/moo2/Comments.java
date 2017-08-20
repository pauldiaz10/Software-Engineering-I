package com.example.paul.moo2;

import android.content.Intent;
import android.os.*;
import android.support.v7.app.AppCompatActivity;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import android.view.View;
import android.widget.*;

import java.util.ArrayList;
import java.util.List;

public class Comments extends AppCompatActivity {
    private User movie;
    private User user;

    private Button browseButton;
    private Button pointsButton;
    private Button sendCritiqueButton;

    private EditText critiqueIt;
    private TextView critiques;
    private TextView title;

    private Firebase ref;
    private String child;
    private String theCritique;

    ArrayList<String> messageList = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        Firebase.setAndroidContext(this);

        Intent intent = getIntent();
        movie = (User) intent.getExtras().getSerializable("Movie");
        user = (User) intent.getExtras().getSerializable("Profile");

        //DIRECT back to browseMovie class
        browseButton = (Button) findViewById(R.id.browseButton);
        browseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(Comments.this, BrowseMovie.class);
                registerIntent.putExtra("Movie", movie);
                registerIntent.putExtra("Profile", user);
                Comments.this.startActivity(registerIntent);
            }
        });

        //We set Title
        title = (TextView) findViewById(R.id.titleMovie);
        if(movie != null){
            title.setText(movie.getFullName());
        }

        //We update the points
        //Will update the context of Moopoints
        pointsButton = (Button) findViewById(R.id.pointsButton);
        if(user != null) {
            int moopoints = user.getMooPoints();
            pointsButton.setText("Points: "+ moopoints + "");
        }


        //We a user leave a comment/critique on a movie,
        //We have to update the list of messages
        critiqueIt = (EditText) findViewById(R.id.critiqueMovie);
        if(critiqueIt != null){
            theCritique = critiqueIt.getText().toString();
        }

        update();

        child = "";
        if(movie != null){
            child = movie.getFullName();
            //critiqueIt.setText("", TextView.BufferType.EDITABLE);
        }
        //Send Button and add the critique into the firebase
        sendCritiqueButton = (Button) findViewById(R.id.sendCritique);
        if(sendCritiqueButton != null){
        sendCritiqueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(critiqueIt != null && !child.equals("")){
                    final Firebase firebaseRef = new Firebase("https://mooapp.firebaseio.com/Comments").child(child);
                    firebaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if(dataSnapshot.exists()){
                                final Firebase ref = new Firebase("https://mooapp.firebaseio.com/Comments");

                                ref.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {

                                        //ref.child(movie.getFullName()+ "")
                                        String path = "https://mooapp.firebaseio.com/Comments" + movie.getFullName();
                                        final Firebase inseFire = new Firebase(path);

                                        //ArrayList<String> temp = (ArrayList<String>)inseFire.child("messageList").getValue();
                                        for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                                            String test = (String) snapshot.child("movie/fullName").getValue();

                                            if(movie != null){
                                                if(movie.getFullName().equals(test)){
                                                    messageList = (ArrayList<String>) snapshot.child("messageList").getValue();
                                                    messageList.add(theCritique);

                                                    //inseFire.child("messageList").setValue(messageList);
                                                    critiqueIt.setText(" ");

                                                    updatePoints();
                                                    update();
                                                    return;
                                                }else{
                                                    critiques = (TextView) findViewById(R.id.critiques);
                                                    critiques.setText("No comment yet");
                                                }
                                            }
                                        }
                                    }
                                    @Override
                                    public void onCancelled(FirebaseError firebaseError) {}
                                });

                            }else{

                                critiques = (TextView) findViewById(R.id.critiques);
                                critiques.setText("No comment yet");

                            }
                        }
                        @Override
                        public void onCancelled(FirebaseError firebaseError) {

                        }
                    });
                }else{
                    //critiques = (TextView) findViewById(R.id.critiques);
                    //critiques.setText("No comment yet end");
                }
            }
        });
        }else{
            critiques = (TextView) findViewById(R.id.critiques);
            critiques.setText("No comment yet");
        }

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
    }

    public void update(){

        //Here we update the page with the critiques
        String childUpdate = "";
        if(movie != null){
            childUpdate = movie.getFullName();
        }
        ref = new Firebase("https://mooapp.firebaseio.com/");
        final Firebase firebaseRef = new Firebase("https://mooapp.firebaseio.com/Comments").child(childUpdate);
        firebaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){

                    Firebase ref = new Firebase("https://mooapp.firebaseio.com/Comments");

                    ref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {


                            String message = "";
                            User movieFire;
                            User userFire;

                            for(DataSnapshot snapshot : dataSnapshot.getChildren()){

                                //movieFire = (User)
                                String test = (String) snapshot.child("movie/fullName").getValue();
                                if(movie != null){
                                    //if(movieFire != null){
                                    String full = movie.getFullName();
                                    if(movie.getFullName().equals(test)){
                                        messageList = (ArrayList<String>) snapshot.child("messageList").getValue();

                                        for(int i = 0; i < messageList.size(); i++){
                                            message += i + 1 + " " +  messageList.get(i) + "\n\n";
                                        }


                                        critiques = (TextView) findViewById(R.id.critiques);
                                        critiques.setText(message);
                                    }
                                    //}else{
                                    //critiques = (TextView) findViewById(R.id.critiques);
                                    //critiques.setText("No comments yet in");
                                    //}
                                }
                            }
                        }
                        @Override
                        public void onCancelled(FirebaseError firebaseError) {}
                    });

                }else{

                    critiques = (TextView) findViewById(R.id.critiques);
                    critiques.setText("No comment yet");
                }
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }

    public void updatePoints(){
        final String field = user.getUserName();

        final Firebase refUser = new Firebase("https://mooapp.firebaseio.com/Users");
        refUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){

                    ref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                                //if(field.equals(snapshot.child())){}
                            }
                        }
                        @Override
                        public void onCancelled(FirebaseError firebaseError) {}
                    });

                }else{

                    critiques = (TextView) findViewById(R.id.critiques);
                    critiques.setText("No comment yet");
                }
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }
}
