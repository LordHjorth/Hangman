package com.example.hjorth.hangman;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Game_level_frag extends Fragment implements View.OnClickListener {

    private Button next;

    Game_frag playGame;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.game_level_chooser_frag, container, false);

        playGame = new Game_frag();

        next = view.findViewById(R.id.next);
        next.setOnClickListener(this);


        return view;
    }

    @Override
    public void onClick(View v) {

        if(v == next){
            //TODO: Close fragment and start new game
            //TODO: Add bundle
            Bundle b = new Bundle();
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragment, playGame).addToBackStack(null).commit();


        }

    }


}
