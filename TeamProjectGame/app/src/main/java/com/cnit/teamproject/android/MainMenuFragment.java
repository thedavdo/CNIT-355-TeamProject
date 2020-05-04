package com.cnit.teamproject.android;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.cnit.teamproject.R;

public class MainMenuFragment extends Fragment {

    private MediaPlayer mainMenuSong;

    public interface Callbacks {
        void onStartPress();
        void onExitPress();
        void onScoresPress();
    }

    private Callbacks callbacks;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        callbacks = (Callbacks) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();

        callbacks = null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main_menu, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mainMenuSong = MediaPlayer.create(getContext(),R.raw.traffic_jam);
        mainMenuSong.start();
        mainMenuSong.setLooping(true);

        Button exitGame = view.findViewById(R.id.exit_game_button);
        exitGame.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Toast.makeText(getContext(), "exit game", Toast.LENGTH_SHORT).show();
                callbacks.onExitPress();
            }
        });

        Button scoresView = view.findViewById(R.id.scores_button);
        scoresView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Toast.makeText(getContext(), "start game", Toast.LENGTH_SHORT).show();
                callbacks.onScoresPress();
            }
        });

        Button newGame = view.findViewById(R.id.new_game_button);
        newGame.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Toast.makeText(getContext(), "start game", Toast.LENGTH_SHORT).show();
                callbacks.onStartPress();
            }
        });
    }
}
