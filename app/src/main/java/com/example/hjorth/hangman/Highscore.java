package com.example.hjorth.hangman;

import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.toMap;


public class Highscore {

    //player specific
    private long score;
    private String username;

    private String guessedWord = "";

    //for calculations
    private long startTimeInSec;
    private final int MULTIPLIER = 100;
    private final int TO_SEC_CONV = 1000;

    public Highscore(String name, Game_logic game) {
        startTimeInSec = game.getStartTime();
        setUsername(name);
        calculateScore(game.getOrdet().length(), game.getAntalKorrekteBogstaver(), game.getAntalKorrekteBogstaver());
    }

    public Highscore(String name, long score) {
        this.username = name;
        this.score = score;
    }

    private void calculateScore(int wordLength, int correctGuesses, int wrongGuesses) {
        long endTimeInSec = Calendar.getInstance().getTimeInMillis();
        this.score = (wordLength + correctGuesses) * MULTIPLIER - ((startTimeInSec - endTimeInSec) / TO_SEC_CONV * wrongGuesses);
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

    public void setGuessedWord(String guessedWord) {
        this.guessedWord = guessedWord;
    }

    public void insertPrefs(String word) {
        setGuessedWord(word);

        Set<String> players = HighscoreSharedPreferences.prefs.getStringSet("playerNames", new HashSet<>());
        players.add(username);

        SharedPreferences.Editor editor = HighscoreSharedPreferences.prefs.edit();
        editor.putStringSet("playerNames", players);
        if (username.equals("") || username == null) {
            username = "¿Unknown?";
        }

        editor.putString(username, username);
        editor.putLong(username, score);
        editor.putString(username + "word", guessedWord);
        editor.apply();

    }

    public static ArrayList<Highscore> getHighscores() {

        Set players = HighscoreSharedPreferences.prefs.getStringSet("playerNames", new HashSet<>());
        HashMap<String, Long> scores = new HashMap<>();
        for (Object player : players) {
            scores.put(player.toString(), HighscoreSharedPreferences.prefs.getLong(player.toString(), 0));
        }

        Map<String, Long> sortedScores = scores
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));


        ArrayList<Highscore> highscoreList = new ArrayList<>();
        for (Map.Entry<String, Long> entry : sortedScores.entrySet()) {
            highscoreList.add(new Highscore(entry.getKey(), entry.getValue()));
        }
        return highscoreList;
    }

    public static String getGuessedWord(String username) {
        return HighscoreSharedPreferences.prefs.getString(username + "word", "");
    }
}
