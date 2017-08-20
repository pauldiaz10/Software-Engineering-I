package com.example.paul.moo2;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

public class BrowseMovie extends AppCompatActivity implements Serializable{
    private Button logoutButton;
    private Button mooPoints;
    private Button profile;
    User user;
    User movieUser;
    private Firebase ref;
    private Movie movie;

    private List<String> moviePosters = new ArrayList<>();
    private List<Movie> myMovies = new ArrayList<>();
    int count = 0;

    List<String> drawPic = new ArrayList<>();

    List<String> titles = new ArrayList<>();
    List<String> synopsisList = new ArrayList<>();
    List<ArrayList<String>> castList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_movie);
        Firebase.setAndroidContext(this);

        Intent intent = getIntent();
        user = (User) intent.getExtras().getSerializable("Profile");
        movieUser = (User) intent.getExtras().getSerializable("Movie");

        logoutButton = (Button)findViewById(R.id.lougOutbutton);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(BrowseMovie.this, MainActivity.class);
                BrowseMovie.this.startActivity(registerIntent);
            }
        });

        //Will update the context of Moopoints
        if(user != null) {
            int moopoints = user.getMooPoints();
            mooPoints = (Button) findViewById(R.id.points);
            mooPoints.setText("Points" + ": " + moopoints);
        }
        //
        profile = (Button) findViewById(R.id.etProf);
        profile.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent registerIntent = new Intent(BrowseMovie.this, Prof.class);
                registerIntent.putExtra("Profile", user);
                BrowseMovie.this.startActivity(registerIntent);
            }
        });

        titleM();
        setMovieTitles();
        populateMovieList();
        setMovieTitles();
        populateListView();
        registerClickCallback();
    }

    private void populateMovieList(){

        ref = new Firebase("https://mooapp.firebaseio.com/Movies");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    String title = (String) snapshot.child("title").getValue();
                    String synopsis = (String)snapshot.child("synopsis").getValue();
                    ArrayList<String> cast = (ArrayList<String>) snapshot.child("castList").getValue();


                    titles.add(title);
                    synopsisList.add(synopsis);
                    castList.add(cast);

                    //String test = "R.drawable" + drawPic.get(count);
                    int id = getResources().getIdentifier(drawPic.get(count),
                            "drawable", getPackageName());
                    count++;

                    myMovies.add(new Movie(title, synopsis, cast, id));
                    /*myMovies.add(new Movie(title, synopsis, cast, R.drawable.batman));
                    myMovies.add(new Movie(title, synopsis, cast, R.drawable.captai));
                    myMovies.add(new Movie(title, synopsis, cast, R.drawable.keanuu));
                    myMovies.add(new Movie(title, synopsis, cast, R.drawable.mother));
                    myMovies.add(new Movie(title, synopsis, cast, R.drawable.ratche));
                    myMovies.add(new Movie(title, synopsis, cast, R.drawable.the_b));
                    myMovies.add(new Movie(title, synopsis, cast, R.drawable.the_h));
                    myMovies.add(new Movie(title, synopsis, cast, R.drawable.the_j));
                    myMovies.add(new Movie(title, synopsis, cast, R.drawable.zooto));*/

                   /* myMovies.add(new Movie(title, synopsis, cast, R.drawable.barber));
                    myMovies.add(new Movie(title, synopsis, cast, R.drawable.batman));
                    myMovies.add(new Movie(title, synopsis, cast, R.drawable.captai));
                    myMovies.add(new Movie(title, synopsis, cast, R.drawable.keanuu));
                    myMovies.add(new Movie(title, synopsis, cast, R.drawable.mother));
                    myMovies.add(new Movie(title, synopsis, cast, R.drawable.ratche));
                    myMovies.add(new Movie(title, synopsis, cast, R.drawable.the_b));
                    myMovies.add(new Movie(title, synopsis, cast, R.drawable.the_h));
                    myMovies.add(new Movie(title, synopsis, cast, R.drawable.the_j));
                    myMovies.add(new Movie(title, synopsis, cast, R.drawable.zooto));*/
                    /*int index = 0;
                        String tempTitle3 = tempTitle2.substring(0, index);
                        int idPoster = getResources().getIdentifier(tempTitle3, "drawable", getPackageName());*/

                }
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }

    private void populateListView(){
        ArrayAdapter<Movie> adapter = new MyListAdapter();
        ListView list = (ListView) findViewById(R.id.listMovies);
        list.setAdapter(adapter);

    }

    private void registerClickCallback(){
        ListView list = (ListView) findViewById(R.id.listMovies);
        assert list != null;
        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id){
                int tempS = 0;
                if(user != null){
                    tempS = user.getMooPoints();
                }
                if(myMovies != null){
                    movie = (Movie) myMovies.get(position);
                    User aMovie = new User(movie.getTitle(), movie.getSynopsis(),drawPic, castList, tempS, position);

                    Intent registerIntent = new Intent(BrowseMovie.this, showmovie.class);
                    registerIntent.putExtra("Movie", aMovie);
                    registerIntent.putExtra("Profile", user);
                    BrowseMovie.this.startActivity(registerIntent);
                }
                //String message = "You clicked position " + position + "";
                //Toast.makeText(BrowseMovie.this, message, Toast.LENGTH_LONG).show();

            }
        });

    }

    private class MyListAdapter extends ArrayAdapter<Movie>{
        public MyListAdapter(){
            super(BrowseMovie.this, R.layout.item_view, myMovies);
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            //We make we have a view to work with (may have given null)
            View itemView = convertView;
            if(itemView == null){
                itemView = getLayoutInflater().inflate(R.layout.item_view, parent, false);
            }
            //Find the movie to work with
            Movie currentMovie = myMovies.get(position);

            //Fill the view
            ImageView imageView = (ImageView) itemView.findViewById(R.id.item_icon);
            imageView.setImageResource(currentMovie.getPoster());

            TextView makeText = (TextView) itemView.findViewById(R.id.item_txtMake);
            makeText.setText(currentMovie.getTitle());

            TextView yearText = (TextView) itemView.findViewById(R.id.item_txtYear);
            yearText.setText("");

            return itemView;
        }
    }
    public void setMovieTitles(){
        for(int i = 0; i < myMovies.size(); i++){
            myMovies.get(i).setTitle(titles.get(i));
            myMovies.get(i).setSynopsis(synopsisList.get(i));
            myMovies.get(i).setCastList(castList.get(i));
        }
    }

    public void titleM(){
        drawPic.add("barber");
        drawPic.add("batman");
        drawPic.add("captai");
        drawPic.add("keanuu");
        drawPic.add("mother");
        drawPic.add("ratche");
        drawPic.add("the_b");
        drawPic.add("the_h");
        drawPic.add("the_j");
        drawPic.add("zooto");
    }

}
