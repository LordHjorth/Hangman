package com.example.hjorth.hangman;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class Game_frag extends Fragment implements View.OnClickListener {

    private Game_logic game;

    private ImageView gallow;
    private Button q, w, e, r, t, y, u, i, o, p, å, a, s, d, f, g, h, j, k, l, æ, ø, z, x, c, v, b, n, m;
    private TextView guesses, correctWord;

    private MediaPlayer media;

    private String level;

    private Game_new_frag newGameOption;

    private final static int MAX_VOLUME = 8;
    private float volume;
    private int soundLevel = 1;

    private List<Integer> gameStages;

    private final String winningMessage = "EEEEEEEEY! Congrats! You won!";
    private final String losingMessage = "You're a loser.";

    private Highscore_frag highscore_frag;
    private Highscore score;

    private String player_name = "Unkown";

    private AlertDialog.Builder dialogBuilder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.game_frag, container, false);

        newGameOption = new Game_new_frag();

        //region Keyboard initializer
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
        //endregion

        //region Dialog initializer
        dialogBuilder = new AlertDialog.Builder(getActivity());
        final EditText input = new EditText(getActivity());
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        dialogBuilder.setView(input);

        // Set up the buttons
        dialogBuilder.setPositiveButton("OKAY!", (dialog, which) -> positiveDialogButtonClick(input));
        dialogBuilder.setNegativeButton("Skip", (dialog, which) -> dialog.cancel());
        //endregion


        guesses = view.findViewById(R.id.gaet);
        correctWord = view.findViewById(R.id.rigtigtOrd);

        gallow = view.findViewById(R.id.galgeBillede);

        //region Array of gallow picturs
        //Creates a List of the galge images.
        gameStages = new ArrayList<>();
        gameStages.add(R.drawable.start);
        gameStages.add(R.drawable.p1);
        gameStages.add(R.drawable.p2);
        gameStages.add(R.drawable.p3);
        gameStages.add(R.drawable.p4);
        gameStages.add(R.drawable.p5);
        gameStages.add(R.drawable.p6);
        //endregion

        level = "Intermediate";


        media = new MediaPlayer();

        //Starts the game

        newGame();

        return view;
    }

    private void positiveDialogButtonClick(EditText input){
        player_name = input.getText().toString();
        score = new Highscore(player_name, game, getActivity());

        score.insertPrefs();
        score.getPrefs();
        System.out.println("The word was: " + game.getOrdet() + "\nScore: " + score.getScore());
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            //region Case for keyboard case
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
            //endregion
                if (game.erSpilletSlut()) {
                    break;
                }
                //handles the button pressed.
                keyboardPressed(v);
                isGameOver();
                break;
        }
    }

    private void isGameOver() {
        if (game.erSpilletSlut()) {
            int musicID;
            Bundle b = new Bundle();
            if (game.erSpilletVundet()) {
                musicID = R.raw.win;
                b.putString("result", winningMessage);
            } else {
                musicID = R.raw.lose;
                b.putString("result", losingMessage);
            }
            dialogBuilder.show();

            changeSound(musicID);
            if(Settings_frag.isMusicEnabled()){
                media.start();
            }

            b.putString("level", level);
            newGameOption.setArguments(b);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.fragment, newGameOption).addToBackStack(null).commit();

        }
    }

    private void changeSound(int resID) {
        if(media.isPlaying())
            media.stop();

        media = MediaPlayer.create(getActivity(), resID);
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

        if(!game.getBrugteBogstaver().contains(guess)){
            game.gætBogstav(guess);
            if (game.erSidsteBogstavKorrekt()) {
                correctGuess(guess);
            } else {
                wrongGuess(guess);
            }
            if(guesses.getText().equals("You haven't guessed yet!")){
                guesses.setText("You've gueesed on: ".concat(", ".concat(guess)));
            }
            else{
                guesses.setText(guesses.getText().toString().concat(", ".concat(guess)));
            }
        }
    }

    private void correctGuess(String guess){
        Toast.makeText(getActivity(), guess + " was correct!", Toast.LENGTH_SHORT).show();
        correctWord.setText(game.getSynligtOrd());
    }

    private void wrongGuess(String guess){
        //Starts playing the fire crackling
        if (!media.isPlaying() && Settings_frag.isMusicEnabled()) {
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
        Toast.makeText(getActivity(), guess + " was wrong!", Toast.LENGTH_SHORT).show();
    }

    public void newGame() {
        //set new gameLevel
        Bundle bundle = this.getArguments();

        if(bundle != null)
        {
            level = bundle.getString("level");
        }
        game = new Game_logic();

        getWords(level);

        guesses.setText("You haven't guessed yet!");

        changeSound(R.raw.firecrackle);
        soundLevel = 1;
        media.setLooping(true);
        media.setVolume(soundLevel, soundLevel);
    }

    public void getWords(String level) {

        new MyAsyncTask(this).execute();

    }



    //Create an inner class of an asyncTask
    //region AsyncTask
    private static class MyAsyncTask extends  AsyncTask<Void, Void, Void>{

        private WeakReference<Game_frag> wr;
        Game_frag game_frag;

        MyAsyncTask(Game_frag newTask){
            wr = new WeakReference<>(newTask);
            game_frag = wr.get();
        }



        @Override
        protected Void doInBackground(Void... voids) {
            try {
                game_frag.game.hentOrdFraDr(game_frag.level);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            System.out.println("pre execute");
            game_frag.correctWord.setText("Loading...");
        }

        @Override
        protected void onPostExecute(Void v) {
            System.out.println("post execute");
            game_frag.correctWord.setText(game_frag.game.getSynligtOrd());
        }
    }
    //endregion
}

