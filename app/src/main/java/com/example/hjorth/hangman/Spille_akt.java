package com.example.hjorth.hangman;

import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Spille_akt extends AppCompatActivity implements View.OnClickListener{

    Galgelogik game;

    ImageView galge;
    Button forsoegKnap;
    TextInputEditText input;
    TextView gaet, rigtigtOrd;
    //MediaPlayer media;

    List<Integer> gameStages;


   @Override
   protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_spil_akt);

       forsoegKnap = findViewById(R.id.forsoegKnap);
       input = findViewById(R.id.input);
       gaet = findViewById(R.id.gaet);
       rigtigtOrd = findViewById(R.id.rigtigtOrd);

       galge = findViewById(R.id.galgeBillede);

       //Creates a List of the galge images.
       gameStages = new ArrayList<>();
       gameStages.add(R.drawable.start);
       gameStages.add(R.drawable.p1);
       gameStages.add(R.drawable.p2);
       gameStages.add(R.drawable.p3);
       gameStages.add(R.drawable.p4);
       gameStages.add(R.drawable.p5);
       gameStages.add(R.drawable.p6);

       //media = MediaPlayer.create(this, R.ResourceFileName.SoundFileName);

       forsoegKnap.setOnClickListener(this);


       game = new Galgelogik();
       gameOn();

   }

    @Override
    public void onClick(View v) {

       String quess = input.getText().toString();


       if(v == forsoegKnap){
           //v.playSoundEffect(9);
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
                   int stage = game.getAntalForkerteBogstaver();
                   if(stage <= gameStages.size()) {
                       galge.setImageResource(gameStages.get(stage));
                   }
                   else {
                       galge.setImageResource(gameStages.get(6));
                   }
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
