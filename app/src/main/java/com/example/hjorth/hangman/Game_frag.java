package com.example.hjorth.hangman;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Game_frag extends Fragment implements View.OnClickListener {

    Game_logic game;

    ImageView gallow;
    Button q, w, e, r, t, y, u, i, o, p, å, a, s, d, f, g, h, j, k, l, æ, ø, z, x, c, v, b, n, m;
    TextView guesses, correctWord;
    MediaPlayer media;

    Game_opt_frag newGameOption;

    private String[] gameLevel = {"Beginner", "Intermediate", "Pro"};

    private final static int MAX_VOLUME = 8;
    private float volume;
    private int soundLevel = 1;

    List<Integer> gameStages;

    private final String winningMessage = "EEEEEEEEY! Congrats! You won!";
    private final String losingMessage = "You're a loser.";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.game_frag, container, false);

        newGameOption = new Game_opt_frag();

        q = view.findViewById(R.id.q);
        q.setOnClickListener(this);
        w = view.findViewById(R.id.w);
        w.setOnClickListener(this);
        e = view.findViewById(R.id.e);
        e.setOnClickListener(this);
        r = view.findViewById(R.id.r);
        r.setOnClickListener(this);
        t = view.findViewById(R.id.t);
        t.setOnClickListener(this);
        y = view.findViewById(R.id.y);
        y.setOnClickListener(this);
        u = view.findViewById(R.id.u);
        u.setOnClickListener(this);
        i = view.findViewById(R.id.i);
        i.setOnClickListener(this);
        o = view.findViewById(R.id.o);
        o.setOnClickListener(this);
        p = view.findViewById(R.id.p);
        p.setOnClickListener(this);
        å = view.findViewById(R.id.å);
        å.setOnClickListener(this);
        a = view.findViewById(R.id.a);
        a.setOnClickListener(this);
        s = view.findViewById(R.id.s);
        s.setOnClickListener(this);
        d = view.findViewById(R.id.d);
        d.setOnClickListener(this);
        f = view.findViewById(R.id.f);
        f.setOnClickListener(this);
        g = view.findViewById(R.id.g);
        g.setOnClickListener(this);
        h = view.findViewById(R.id.h);
        h.setOnClickListener(this);
        j = view.findViewById(R.id.j);
        j.setOnClickListener(this);
        k = view.findViewById(R.id.k);
        k.setOnClickListener(this);
        l = view.findViewById(R.id.l);
        l.setOnClickListener(this);
        æ = view.findViewById(R.id.æ);
        æ.setOnClickListener(this);
        ø = view.findViewById(R.id.ø);
        ø.setOnClickListener(this);
        z = view.findViewById(R.id.z);
        z.setOnClickListener(this);
        x = view.findViewById(R.id.x);
        x.setOnClickListener(this);
        c = view.findViewById(R.id.c);
        c.setOnClickListener(this);
        v = view.findViewById(R.id.v);
        v.setOnClickListener(this);
        b = view.findViewById(R.id.b);
        b.setOnClickListener(this);
        n = view.findViewById(R.id.n);
        n.setOnClickListener(this);
        m = view.findViewById(R.id.m);
        m.setOnClickListener(this);

        guesses = view.findViewById(R.id.gaet);
        correctWord = view.findViewById(R.id.rigtigtOrd);

        gallow = view.findViewById(R.id.galgeBillede);

        //Creates a List of the galge images.
        gameStages = new ArrayList<>();
        gameStages.add(R.drawable.start);
        gameStages.add(R.drawable.p1);
        gameStages.add(R.drawable.p2);
        gameStages.add(R.drawable.p3);
        gameStages.add(R.drawable.p4);
        gameStages.add(R.drawable.p5);
        gameStages.add(R.drawable.p6);

        //TODO: Get media player to work
        media = MediaPlayer.create(getActivity(), R.raw.firecrackle);
        media.setLooping(true);
        media.setVolume(soundLevel, soundLevel);


        newGame();

        return view;
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.q:
            case R.id.w:
            case R.id.e:
            case R.id.r:
            case R.id.t:
            case R.id.y:
            case R.id.u:
            case R.id.i:
            case R.id.o:
            case R.id.p:
            case R.id.å:
            case R.id.a:
            case R.id.s:
            case R.id.d:
            case R.id.f:
            case R.id.g:
            case R.id.h:
            case R.id.j:
            case R.id.k:
            case R.id.l:
            case R.id.æ:
            case R.id.ø:
            case R.id.z:
            case R.id.x:
            case R.id.c:
            case R.id.v:
            case R.id.b:
            case R.id.n:
            case R.id.m:

                if (game.erSpilletSlut()) {
                    break;
                }

                //handles the butten pressed.
                keyboardPressed(v);
                isGameOver();

                break;
        }
    }

    private void isGameOver() {
        if (game.erSpilletSlut()) {
            if (game.erSpilletVundet()) {
                changeSound(R.raw.win);
                Toast.makeText(getActivity(), winningMessage, Toast.LENGTH_LONG).show();
            } else {
                changeSound(R.raw.lose);
                Toast.makeText(getActivity(), losingMessage, Toast.LENGTH_LONG).show();
            }
            //TODO: Add to option for new game fragment

            //TODO: move fragment transaction to Game_frag
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragment, newGameOption).addToBackStack(null).commit();

        }
    }

    private void changeSound(int resID) {
        media.stop();
        media = MediaPlayer.create(getActivity(), resID);
        media.start();
    }

    private void changeImage() {
        int stage = game.getAntalForkerteBogstaver();
        if (stage < gameStages.size()) {
            gallow.setImageResource(gameStages.get(stage));
        }
    }

    private void keyboardPressed(View v) {
        Button pressed = v.findViewById(v.getId());
        String guess = pressed.getText().toString();

        //Toast.makeText(this, "You have quessed on: " + guess, Toast.LENGTH_LONG).show();
        game.gætBogstav(guess);
        if (game.erSidsteBogstavKorrekt()) {
            Toast.makeText(getActivity(), guess + " var korrekt!", Toast.LENGTH_SHORT).show();
            correctWord.setText(game.getSynligtOrd());
        } else {
            //Starts playing the fire crackling
            if (!media.isPlaying()) {
                media.start();
            }
            //Set the volume louder and louder for each wrong answer.
            if (soundLevel < 10) {
                soundLevel++;
            }
            volume = (float) (1 - (Math.log(MAX_VOLUME - soundLevel) / Math.log(MAX_VOLUME)));
            media.setVolume(volume, volume);

            //Updates the image for each wrong.
            changeImage();

            //Prints message
            Toast.makeText(getActivity(), guess + " var forkert!", Toast.LENGTH_SHORT).show();
        }
        String newText = guesses.getText().toString();
        guesses.setText(newText.concat(", ".concat(guess)));

    }

    public void newGame() {
        //set new gameLevel
        game = new Game_logic(gameLevel[1]);

        correctWord.setText(game.getSynligtOrd());
        guesses.setText("Du har ikke gættet endnu!");
        changeSound(R.raw.firecrackle);
        media.setLooping(true);


    }
}

