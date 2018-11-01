package com.example.hjorth.hangman;

import java.util.Calendar;
import java.util.Date;

public class Highscore {

    private String username = "";
    private long startTimeInSec;

    private final int MULTIPLIER = 100;
    private final int TO_SEC_CONV = 1000;

    public Highscore(){
        startTimeInSec = Calendar.getInstance().getTimeInMillis();
    }

    public long calculateScore(int wordLength, int correctGuesses, int wrongGuesses){
        long endTimeInSec = Calendar.getInstance().getTimeInMillis();

        return (wordLength + correctGuesses) * MULTIPLIER - ((startTimeInSec-endTimeInSec)/TO_SEC_CONV * wrongGuesses);
    }

}
