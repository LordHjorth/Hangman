package com.example.hjorth.hangman;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Highscore extends Application {

    SharedPreferences prefs;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    //player specific
    private long score;
    private String username;

    //for calculations
    private long startTimeInSec;
    private final int MULTIPLIER = 100;
    private final int TO_SEC_CONV = 1000;



    public Highscore(String name, Game_logic game, Context context){
        startTimeInSec = Calendar.getInstance().getTimeInMillis();
        this.username = name;
        calculateScore(game.getOrdet().length(), game.getAntalKorrekteBogstaver(), game.getAntalKorrekteBogstaver());
        prefs = context.getSharedPreferences("Highscores", MODE_PRIVATE);
    }

    public void calculateScore(int wordLength, int correctGuesses, int wrongGuesses){
        long endTimeInSec = Calendar.getInstance().getTimeInMillis();
        score = (wordLength + correctGuesses) * MULTIPLIER - ((startTimeInSec-endTimeInSec)/TO_SEC_CONV * wrongGuesses);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getScore() {
        return score;
    }

    public void insertPrefs(){
        Set players = prefs.getStringSet("playerNames", new HashSet<>());
        players.add(username);

        SharedPreferences.Editor editor = prefs.edit();
        editor.putStringSet("playerNames", players);
        editor.putString(username, username);
        editor.putLong(username, score);
        editor.commit();
    }

    public void getPrefs(){
        Set players = prefs.getStringSet("playerNames", new HashSet<>());
        HashMap<String, Long> scores = new HashMap<>();
        for(Object player : players){
            scores.put(player.toString(), prefs.getLong(player.toString(), 0));
            System.out.println("name: " + player.toString() + "\nScore:  " + scores.get(player.toString()));
        }

        System.out.println("Score set: " + scores);
    }

}
