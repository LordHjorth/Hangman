package com.example.hjorth.hangman;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class Highscore_frag extends Fragment {

    ListView highscoresListView;
    HighscoreListAdapater adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.highscore_frag, container, false);

        highscoresListView = view.findViewById(R.id.highscoreList);

        adapter = new HighscoreListAdapater(getActivity(), Highscore.getHighscores());
        highscoresListView.setAdapter(adapter);

        return view;
    }

}