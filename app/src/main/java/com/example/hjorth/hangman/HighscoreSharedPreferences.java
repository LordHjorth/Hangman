package com.example.hjorth.hangman;

import android.app.Application;
import android.content.SharedPreferences;

public class HighscoreSharedPreferences extends Application {

    public static SharedPreferences prefs;

    public HighscoreSharedPreferences() {
        super();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        prefs = getSharedPreferences(getPackageName() + "_Highscores", MODE_PRIVATE);
    }
}
