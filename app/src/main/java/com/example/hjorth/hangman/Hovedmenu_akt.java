package com.example.hjorth.hangman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Hovedmenu_akt extends AppCompatActivity implements View.OnClickListener {

    Button playBtn, settingsBtn, scoresBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hovedmenu_akt);

        playBtn = findViewById(R.id.PlayBtn);
        settingsBtn = findViewById(R.id.SettingsBtn);
        scoresBtn = findViewById(R.id.ScoresBtn);

        playBtn.setOnClickListener(this);
        settingsBtn.setOnClickListener(this);
        scoresBtn.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        if(v == playBtn){
            //go to play
            Intent i = new Intent(this, Spille_akt.class);
            startActivity(i);
        }
        else if(v == settingsBtn){
            //go to settings
        }
        else if(v == scoresBtn){
            //go to scores
            Intent i = new Intent(this, Highscore_akt.class);
            startActivity(i);
        }
    }
}
