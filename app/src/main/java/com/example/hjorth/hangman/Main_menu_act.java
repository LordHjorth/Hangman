package com.example.hjorth.hangman;

import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Main_menu_act extends AppCompatActivity implements View.OnClickListener {

    private Button helpBtn, settingsBtn, scoresBtn, playBtn;
    private Fragment playFrag, settingsFrag, helpFrag, scoreFrag;
    public static int exitGame = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_act);

        playFrag = new Game_level_frag();
        settingsFrag = new Settings_frag();
        helpFrag = new Help_frag();
        scoreFrag = new Highscore_frag();

        getFragmentManager().beginTransaction().add(R.id.fragment, playFrag).commit();

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
        exitGame = 0;

        if (v == helpBtn) {
            getFragmentManager().beginTransaction().replace(R.id.fragment, helpFrag).commit();
        }
        if (v == settingsBtn) {
            getFragmentManager().beginTransaction().replace(R.id.fragment, settingsFrag).commit();
        }
        if (v == scoresBtn) {
            getFragmentManager().beginTransaction().replace(R.id.fragment, scoreFrag).commit();
        }
        if (v == playBtn) {
            getFragmentManager().beginTransaction().replace(R.id.fragment, playFrag).commit();
        }
    }

    @Override
    public void onBackPressed() {
        exitGame++;

        getFragmentManager().popBackStack();
        getFragmentManager().beginTransaction().replace(R.id.fragment, playFrag).addToBackStack(null).commit();

        if (exitGame == 1) {
            Toast.makeText(this, "Press back one more time to exit the game", Toast.LENGTH_SHORT).show();
        }
        if (exitGame == 2) {
            finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        exitGame = 0;
        getFragmentManager().popBackStack();
        getFragmentManager().beginTransaction().replace(R.id.fragment, playFrag).addToBackStack(null).commit();
    }
}
