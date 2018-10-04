package com.example.hjorth.hangman;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Spille_akt extends AppCompatActivity implements View.OnClickListener {

    Galgelogik game;

    ImageView galge;
    Button q, w, e, r, t, y, u, i, o, p, å, a, s, d, f, g, h, j, k, l, æ, ø, z, x, c, v, b, n, m;
    TextView gaet, rigtigtOrd, ord;
    MediaPlayer media;

    private final static int MAX_VOLUME = 10;
    private float volume;
    private int soundLevel = 1;


    List<Integer> gameStages;

    private final String winningMessage = "EEEEEEEEY! Congrats! You won!";
    private final String losingMessage = "You're a fucking loser.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spil_akt);

        q = findViewById(R.id.q);
        q.setOnClickListener(this);
        w = findViewById(R.id.w);
        w.setOnClickListener(this);
        e = findViewById(R.id.e);
        e.setOnClickListener(this);
        r = findViewById(R.id.r);
        r.setOnClickListener(this);
        t = findViewById(R.id.t);
        t.setOnClickListener(this);
        y = findViewById(R.id.y);
        y.setOnClickListener(this);
        u = findViewById(R.id.u);
        u.setOnClickListener(this);
        i = findViewById(R.id.i);
        i.setOnClickListener(this);
        o = findViewById(R.id.o);
        o.setOnClickListener(this);
        p = findViewById(R.id.p);
        p.setOnClickListener(this);
        å = findViewById(R.id.å);
        å.setOnClickListener(this);
        a = findViewById(R.id.a);
        a.setOnClickListener(this);
        s = findViewById(R.id.s);
        s.setOnClickListener(this);
        d = findViewById(R.id.d);
        d.setOnClickListener(this);
        f = findViewById(R.id.f);
        f.setOnClickListener(this);
        g = findViewById(R.id.g);
        g.setOnClickListener(this);
        h = findViewById(R.id.h);
        h.setOnClickListener(this);
        j = findViewById(R.id.j);
        j.setOnClickListener(this);
        k = findViewById(R.id.k);
        k.setOnClickListener(this);
        l = findViewById(R.id.l);
        l.setOnClickListener(this);
        æ = findViewById(R.id.æ);
        æ.setOnClickListener(this);
        ø = findViewById(R.id.ø);
        ø.setOnClickListener(this);
        z = findViewById(R.id.z);
        z.setOnClickListener(this);
        x = findViewById(R.id.x);
        x.setOnClickListener(this);
        c = findViewById(R.id.c);
        c.setOnClickListener(this);
        v = findViewById(R.id.v);
        v.setOnClickListener(this);
        b = findViewById(R.id.b);
        b.setOnClickListener(this);
        n = findViewById(R.id.n);
        n.setOnClickListener(this);
        m = findViewById(R.id.m);
        m.setOnClickListener(this);

        gaet = findViewById(R.id.gaet);
        ord = findViewById(R.id.ord);
        rigtigtOrd = findViewById(R.id.rigtigtOrd);

        galge = findViewById(R.id.galgeBillede);

        //Creates a List of the galge images.
        gameStages = new ArrayList<>();
        gameStages.add(R.drawable.start);
        gameStages.add(R.drawable.p1);
        gameStages.add(R.drawable.p2);
        gameStages.add(R.drawable.p3);
        gameStages.add(R.drawable.p4);
        gameStages.add(R.drawable.p5);
        gameStages.add(R.drawable.p6);

        media = MediaPlayer.create(this, R.raw.firecrackle);
        media.setLooping(true);
        media.setVolume(soundLevel, soundLevel);

        //forsoegKnap.setOnClickListener(this);


        game = new Galgelogik();

        newGame();

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

                Button pressed = findViewById(v.getId());
                String quess = pressed.getText().toString();

                //Checks if the length of the answer matches only 1 character. Shouldn't be a problem after update to button keyboard.
                if (quess.length() != 1) {
                    Toast.makeText(this, "Try again. The entered number must be only one character", Toast.LENGTH_LONG).show();
                    return;
                } else {
                    //Toast.makeText(this, "You have quessed on: " + quess, Toast.LENGTH_LONG).show();
                    game.gætBogstav(quess);
                    if (game.erSidsteBogstavKorrekt()) {
                        Toast.makeText(this, quess + " var korrekt!", Toast.LENGTH_SHORT).show();
                        rigtigtOrd.setText(game.getSynligtOrd());
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
                        int stage = game.getAntalForkerteBogstaver();
                        if (stage <= gameStages.size()) {
                            galge.setImageResource(gameStages.get(stage));
                        } else {
                            galge.setImageResource(gameStages.get(6));
                        }
                        //Prints message
                        Toast.makeText(this, quess + " var forkert!", Toast.LENGTH_SHORT).show();
                    }
                    String newText = gaet.getText().toString();
                    gaet.setText(newText.concat(", ".concat(quess)));

                }
                if (game.erSpilletSlut()) {
                    if (game.erSpilletVundet()) {
                        changeSound(R.raw.win);
                        Toast.makeText(this, winningMessage, Toast.LENGTH_LONG).show();
                    } else {
                        changeSound(R.raw.lose);
                        Toast.makeText(this, losingMessage, Toast.LENGTH_LONG).show();
                    }
                }
                break;
        }
    }

    private void changeSound(int resID) {
        media.stop();
        media = MediaPlayer.create(this, resID);
        media.start();
    }

    private void newGame() {

        game = new Galgelogik();
        rigtigtOrd.setText(game.getSynligtOrd());
        gaet.setText("Du har ikke gættet endnu!");

    }

}
