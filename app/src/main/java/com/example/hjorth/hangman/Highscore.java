package com.example.hjorth.hangman;

import java.util.Calendar;
import java.util.Date;

public class Highscore {

    private long startTimeInSec;
    private int wordLength;

    private final int MULTIPLIER = 100;
    private final int TO_SEC_CONV = 1000;

    public Highscore(){
        startTimeInSec = Calendar.getInstance().getTimeInMillis();
    }

    public long calculateScore(int wordLength, int correctGuesses, int wrongGuesses){
        long endTimeInSec = Calendar.getInstance().getTimeInMillis();

        System.out.println("start time: " + startTimeInSec);
        System.out.println("end time: " + endTimeInSec);
        System.out.println("end time: " + (endTimeInSec-startTimeInSec)/1000);
        System.out.println("word length: " + wordLength);
        System.out.println("wrong guesses: " + correctGuesses);
        System.out.println("wrong guesses: " + wrongGuesses);
        return (wordLength + correctGuesses) * MULTIPLIER - ((startTimeInSec-endTimeInSec)/TO_SEC_CONV * wrongGuesses);
    }

}
