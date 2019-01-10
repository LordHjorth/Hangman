package com.example.hjorth.hangman;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;

public class Game_new_frag extends Fragment implements View.OnClickListener {

    private Button yes, no;

    private Game_level_frag menu;
    private Game_frag newGame;

    private TextView resultText, scoreText;

    private KonfettiView viewKonfetti;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.game_new_option_frag, container, false);

        resultText = view.findViewById(R.id.resultText);
        scoreText = view.findViewById(R.id.scoreText);

        yes = view.findViewById(R.id.Yes);
        no = view.findViewById(R.id.No);

        menu = new Game_level_frag();
        newGame = new Game_frag();

        yes.setOnClickListener(this);
        no.setOnClickListener(this);

        viewKonfetti = view.findViewById(R.id.viewKonfetti);

        setResultText(view);

        return view;
    }

    private void setResultText(View v) {
        String resultMessage = "Great job!";
        boolean won = false;
        Long score = 0L;
        Bundle b = this.getArguments();
        if (b != null) {
            resultMessage = b.getString("result");
            won = b.getBoolean("won");
            score = b.getLong("score");
        }
        if (won) {
            viewKonfetti.build()
                    .addColors(Color.YELLOW, Color.GREEN, Color.MAGENTA)
                    .setDirection(0.0, 359.0)
                    .setSpeed(1f, 5f)
                    .setFadeOutEnabled(true)
                    .setTimeToLive(2000L)
                    .addShapes(Shape.RECT, Shape.CIRCLE)
                    .setPosition(-50f, 50f, -50f, -50f)
                    .stream(300, 5000L);

        }
        resultText.setText(resultMessage);
        scoreText.setText("You scored: " + score);
    }

    @Override
    public void onClick(View v) {

        if (v == yes) {
            //TODO: Close fragment and start new game
            Bundle b = this.getArguments();
            if (b != null) {
                String sameLevel = b.getString("level");
                String sameWebsite = b.getString("website");
                b.putString("level", sameLevel);
                b.putString("website", sameWebsite);
            }
            newGame.setArguments(b);
            getFragmentManager().beginTransaction().replace(R.id.fragment, newGame).commit();
        }
        if (v == no) {
            //TODO: Close fragment and go to Main menu
            getFragmentManager().beginTransaction().replace(R.id.fragment, menu).commit();
        }

    }
}
