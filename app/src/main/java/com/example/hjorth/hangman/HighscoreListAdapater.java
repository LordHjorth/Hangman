package com.example.hjorth.hangman;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class HighscoreListAdapater extends ArrayAdapter<Highscore> {

    public HighscoreListAdapater(Context context, ArrayList<Highscore> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.highscores_listitem, parent, false);
        }

        Highscore score  = getItem(position);
        TextView hname = convertView.findViewById(R.id.score_name);
        hname.setText(score.getUsername());
        TextView hscore = convertView.findViewById(R.id.score_score);
        hscore.setText(String.valueOf(score.getScore()));

        return convertView;
    }
}
