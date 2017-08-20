package com.example.paul.moo2;
import android.text.TextUtils;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

public class Movie {

    private String title;
    private String synopsis;
    private int criticsScore;
    private int poster;
    private String trailer;
    private ArrayList<String> castList;
    private ArrayList<String> messages;

    public Movie(){
        this.poster = 0;
        this.trailer = "";
        this.title = "";
        this.synopsis = "";
        this.criticsScore = 0;
        castList = new ArrayList<>();

        messages = new ArrayList<>();
    }

    public Movie(String title, String synopsis, ArrayList<String> cast, int score){
        this.criticsScore = 0;
        this.trailer = "";
        this.title = title;
        this.synopsis = synopsis;
        this.poster = score;
        castList = cast;

        messages = new ArrayList<>();
    }

    public void setPoster(int poster){
        this.poster = poster;
    }

    public void setTrailer(String trailer){
        this.trailer = trailer;
    }

    public int getPoster(){
        return poster;
    }

    public String getTrailer(){
        return trailer;
    }

    public String getTitle(){
        return title;
    }

    public String getSynopsis(){

        return synopsis;
    }

    public int getCriticsScore(){

        return criticsScore;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setSynopsis(String synopsis){
        this.synopsis = synopsis;
    }

    public void setCastList(ArrayList<String> cast){
        this.castList = cast;
    }
    public ArrayList<String> getCastList(){
        return castList;
    }

    public ArrayList<String> getMessages(){
        return messages;
    }

    public void addCast(String actor){
        castList.add(actor);
    }

    public void addMessage(String message){
        messages.add(message);
    }
    /*public static Movie fromJson(JSONObject jsonObject) throws JSONException {
        Movie b = new Movie();
        try {
            // Deserialize json into object fields
            b.title = jsonObject.getString("title");
            b.year = jsonObject.getInt("year");
            b.synopsis = jsonObject.getString("synopsis");
            b.posterUrl = jsonObject.getJSONObject("posters").getString("thumbnail");
            b.criticsScore = jsonObject.getJSONObject("ratings").getInt("critics_score");
            // Construct simple array of cast names
            b.castList = new ArrayList<String>();
            JSONArray abridgedCast = jsonObject.getJSONArray("abridged_cast");
            for (int i = 0; i < abridgedCast.length(); i++) {
                b.castList.add(abridgedCast.getJSONObject(i).getString("name"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        // Return new object
        return b;
    }

    public static ArrayList<Movie> fromJson(JSONArray jsonArray) {
        ArrayList<Movie> movies = new ArrayList<Movie>(jsonArray.length());
        // Process each result in json array, decode and convert to movie
        // object
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject moviesJson = null;
            try {
                moviesJson = jsonArray.getJSONObject(i);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }

            Movie movie = null;
            try {
                movie = Movie.fromJson(moviesJson);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (movie != null) {
                movies.add(movie);
            }
        }

        return movies;
    }*/
}
