package com.example.hjorth.hangman;

import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class HighscoreListAdapater extends ArrayAdapter<Highscore> implements View.OnClickListener {

    private Highscore score;
    TextView hname, hscore;

    public HighscoreListAdapater(Context context, ArrayList<Highscore> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.highscores_listitem, parent, false);
        }

        score = getItem(position);
        hname = convertView.findViewById(R.id.score_name);
        hname.setText(score.getUsername());
        hname.setOnClickListener(this);
        hscore = convertView.findViewById(R.id.score_score);
        hscore.setText(String.valueOf(score.getScore()));

        return convertView;
    }

    @Override
    public void onClick(View v) {
        TextView item = (TextView)v;
        String guessedWord = Highscore.getGuessedWord(item.getText().toString());
        new MyAsyncTask(item, guessedWord).execute();
    }

    private static class MyAsyncTask extends AsyncTask<Void, Void, Void> {

        TextView view;
        String tempText = "";
        String guessedWord;

        MyAsyncTask(TextView view, String guessedWord){
            this.view = view;
            this.guessedWord = guessedWord;
        }



        @Override
        protected Void doInBackground(Void... voids) {
            try {
                System.out.println("Wait begun");
                Thread.sleep(2000);
                System.out.println("Wait finished");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPreExecute() {
            tempText = view.getText().toString();
            view.setText("The word was: " + guessedWord );
            System.out.println("pre execute");
        }

        @Override
        protected void onPostExecute(Void v) {
            view.setText(tempText);
            System.out.println("post execute");
        }
    }

}
