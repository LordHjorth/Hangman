package com.example.hjorth.hangman;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Game_level_frag extends Fragment implements View.OnClickListener {

    private Button next;

    RadioGroup level;
    RadioButton beginner, intermediate, pro;

    MediaPlayer media;

    Game_frag playGame;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.game_level_chooser_frag, container, false);

        playGame = new Game_frag();

        next = view.findViewById(R.id.next);
        next.setOnClickListener(this);

        level = view.findViewById(R.id.level);
        beginner = view.findViewById(R.id.easy);
        intermediate = view.findViewById(R.id.medium);
        pro = view.findViewById(R.id.hard);

        return view;
    }

    @Override
    public void onClick(View v) {

        if(v == next){
            if(Settings_frag.isMusicEnabled()) {
                media = MediaPlayer.create(getActivity(), R.raw.supbro);
                media.start();
            }

            Bundle b = new Bundle();
            if(beginner.isChecked()){
                b.putString("level", beginner.getText().toString());
            }
            if(intermediate.isChecked()){
                b.putString("level", intermediate.getText().toString());
            }
            if(pro.isChecked()) {
                b.putString("level", pro.getText().toString());
            }

            playGame.setArguments(b);

            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.fragment, playGame).addToBackStack(null).commit();
        }

    }




}
