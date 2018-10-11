package com.example.hjorth.hangman;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Game_level_frag extends Fragment implements View.OnClickListener {

    private Button next;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_level_chooser, container, false);


        next = view.findViewById(R.id.next);
        next.setOnClickListener(this);


        return view;
    }

    @Override
    public void onClick(View v) {

        if(v == next){
            //TODO: Close fragment and start new game

            //manager.beginTransaction().hide();
            //getActivity().
            //getActivity().onBackPressed();
        }

    }


}
