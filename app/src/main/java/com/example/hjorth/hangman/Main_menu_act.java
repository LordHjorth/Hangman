package com.example.hjorth.hangman;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main_menu_act extends AppCompatActivity implements View.OnClickListener {

    private Button helpBtn, settingsBtn, scoresBtn, playBtn;
    private Fragment playFrag, settingsFrag, helpFrag, scoreFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_act);

        playFrag = new Game_level_frag();
        settingsFrag = new Settings_frag();
        helpFrag = new Help_frag();
        scoreFrag = new Highscore_frag();

        getSupportFragmentManager().beginTransaction().add(R.id.fragment, playFrag).commit();

        helpBtn = findViewById(R.id.rules);
        settingsBtn = findViewById(R.id.SettingsBtn);
        scoresBtn = findViewById(R.id.ScoresBtn);
        playBtn = findViewById(R.id.playBtn);

        helpBtn.setOnClickListener(this);
        settingsBtn.setOnClickListener(this);
        scoresBtn.setOnClickListener(this);
        playBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == helpBtn){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment, helpFrag).commit();
        }
        if(v == settingsBtn){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment, settingsFrag).commit();
        }
        if(v == scoresBtn){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment, scoreFrag).commit();
        }
        if(v == playBtn){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment, playFrag).commit();
        }
    }
}
