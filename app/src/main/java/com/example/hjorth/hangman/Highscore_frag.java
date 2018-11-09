package com.example.hjorth.hangman;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

public class Highscore_frag extends Fragment{


    ListView highscoresListView;

    public Highscore_frag(){

    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.highscore_frag, container, false);

        highscoresListView = view.findViewById(R.id.highscoreList);


        HighscoreListAdapater adapater = new HighscoreListAdapater(getActivity(), Highscore.getHighscores());
        highscoresListView.setAdapter(adapater);

        return view;
    }
}