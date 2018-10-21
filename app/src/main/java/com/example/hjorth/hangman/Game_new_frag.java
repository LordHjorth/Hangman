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
import android.widget.TextView;

public class Game_new_frag extends Fragment implements View.OnClickListener {

    private Button yes, no;

    Game_level_frag menu;
    Game_frag newGame;

    private static boolean oneMoreTime = false;

    TextView resultText;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.game_new_option_frag, container, false);

        setResultText(view);

        yes = view.findViewById(R.id.Yes);
        no = view.findViewById(R.id.No);

        menu = new Game_level_frag();
        newGame = new Game_frag();

        yes.setOnClickListener(this);
        no.setOnClickListener(this);


        return view;
    }

    private void setResultText(View v){
        String resultMessage = "Great job!";
        Bundle b = this.getArguments();
        if(b != null){
            resultMessage = b.getString("result");
        }
        resultText = v.findViewById(R.id.resultText);
        resultText.setText(resultMessage);
    }


    @Override
    public void onClick(View v) {

        if(v == yes){
            //TODO: Close fragment and start new game
            Bundle b = this.getArguments();
            if(b != null){
                String sameLevel = b.getString("level");
                b.putString("sameLevel", sameLevel);
            }
            newGame.setArguments(b);
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragment, newGame).addToBackStack(null).commit();
        }
        if(v == no){
            //TODO: Close fragment and go to Main menu
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragment, menu).addToBackStack(null).commit();
        }

    }


}
