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

public class Game_opt_frag extends Fragment implements View.OnClickListener {

    private Button yes, no;

    Game_level_frag menu;
    Game_frag newGame;

    private static boolean oneMoreTime = false;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.game_new_option_frag, container, false);

        yes = view.findViewById(R.id.Yes);
        no = view.findViewById(R.id.No);

        menu = new Game_level_frag();
        newGame = new Game_frag();

        yes.setOnClickListener(this);
        no.setOnClickListener(this);


        return view;
    }

    public static boolean isOneMoreTime() {
        return oneMoreTime;
    }

    @Override
    public void onClick(View v) {

        if(v == yes){
            //TODO: Close fragment and start new game
            oneMoreTime = true;
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragment, newGame).addToBackStack(null).commit();
        }
        if(v == no){
            //TODO: Close fragment and go to Main menu
            //getActivity().onBackPressed();
            oneMoreTime = false;
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragment, menu).addToBackStack(null).commit();
        }

    }


}
