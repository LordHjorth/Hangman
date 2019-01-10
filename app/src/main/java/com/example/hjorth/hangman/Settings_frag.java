package com.example.hjorth.hangman;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

public class Settings_frag extends Fragment implements CompoundButton.OnCheckedChangeListener {

    public static boolean musicEnabled = true;
    private Switch musicOption;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.settings_frag, container, false);

        musicOption = view.findViewById(R.id.musicEnabled);
        musicOption.setOnCheckedChangeListener(this);

        return view;
    }

    public static boolean isMusicEnabled() {
        return musicEnabled;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (buttonView == musicOption) {
            musicEnabled = isChecked;
        }
    }
}
