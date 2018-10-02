package com.example.hjorth.hangman;

import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.SoundEffectConstants;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Spille_akt extends AppCompatActivity implements View.OnClickListener{

    Galgelogik game;

    Button forsoegKnap;
    TextInputEditText input;
    TextView gaet, rigtigtOrd;
    MediaPlayer media;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_spil_akt);

       forsoegKnap = findViewById(R.id.forsoegKnap);
       input = findViewById(R.id.input);
       gaet = findViewById(R.id.gaet);
       rigtigtOrd = findViewById(R.id.rigtigtOrd);

       //media = MediaPlayer.create(this, R.);

       forsoegKnap.setOnClickListener(this);

       game = new Galgelogik();
       gameOn();

   }

    @Override
    public void onClick(View v) {

       String quess = input.getText().toString();

       if(v == forsoegKnap){
           v.playSoundEffect(9);
           if(quess.length() != 1){
               Toast.makeText(this, "Try again. The entered number must be only one character", Toast.LENGTH_LONG).show();
               return;
           }
           else{
               //Toast.makeText(this, "You have quessed on: " + quess, Toast.LENGTH_LONG).show();
               game.gætBogstav(quess);
               if(game.erSidsteBogstavKorrekt()){
                   Toast.makeText(this, quess + " var korrekt!", Toast.LENGTH_LONG).show();
               }
               else{
                   Toast.makeText(this, quess + " var forkert!", Toast.LENGTH_LONG).show();

               }
           }
       }

    }

    private void gameOn(){

       if(game.erSpilletSlut()){
           game = new Galgelogik();
       }
       rigtigtOrd.setText(game.getSynligtOrd());
       gaet.setText("Du har ikke gættet endnu!");

    }

}
