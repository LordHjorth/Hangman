package com.example.hjorth.hangman;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;

public class Game_level_frag extends Fragment implements View.OnClickListener {

    private Button next;

    private Spinner spinner;

    private RadioButton beginner, intermediate, pro;

    private MediaPlayer media;

    private Game_frag playGame;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.game_level_chooser_frag, container, false);


        playGame = new Game_frag();

        spinner = view.findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getActivity(),
                R.array.websites, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        next = view.findViewById(R.id.next);
        next.setOnClickListener(this);

        beginner = view.findViewById(R.id.easy);
        intermediate = view.findViewById(R.id.medium);
        pro = view.findViewById(R.id.hard);

        return view;
    }

    @Override
    public void onClick(View v) {

        if (v == next) {
            if (Settings_frag.isMusicEnabled()) {
                media = MediaPlayer.create(getActivity(), R.raw.supbro);
                media.start();
            }

            Bundle b = new Bundle();
            if (beginner.isChecked()) {
                b.putString("level", beginner.getText().toString());
            }
            if (intermediate.isChecked()) {
                b.putString("level", intermediate.getText().toString());
            }
            if (pro.isChecked()) {
                b.putString("level", pro.getText().toString());
            }

            b.putString("website", spinner.getSelectedItem().toString());
            playGame.setArguments(b);

            getFragmentManager().beginTransaction().replace(R.id.fragment, playGame).commit();
        }

    }


}
