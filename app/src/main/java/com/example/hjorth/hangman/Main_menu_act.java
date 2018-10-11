package com.example.hjorth.hangman;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main_menu_act extends AppCompatActivity implements View.OnClickListener {

    Button rulesBtn, settingsBtn, scoresBtn;
    MediaPlayer welcomeSound;
    Fragment playFrag = new Game_level_frag();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_act);

        getSupportFragmentManager().beginTransaction().add(R.id.fragment, playFrag).commit();

        rulesBtn = findViewById(R.id.rules);
        settingsBtn = findViewById(R.id.SettingsBtn);
        scoresBtn = findViewById(R.id.ScoresBtn);

        welcomeSound = MediaPlayer.create(this, R.raw.supbro);

        rulesBtn.setOnClickListener(this);
        settingsBtn.setOnClickListener(this);
        scoresBtn.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if(v == rulesBtn){
            //go to play
            welcomeSound.start();
            //TODO: Add rules act
            //Intent i = new Intent(this, Game_frag.class);
            //startActivity(i);
        }
        else if(v == settingsBtn){
            //TODO: Add settings act
            //go to settings
        }
        else if(v == scoresBtn){
            //go to scores
            Intent i = new Intent(this, Highscore_act.class);
            startActivity(i);
        }
    }
}
