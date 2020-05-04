package com.cnit.teamproject.android;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.cnit.teamproject.R;

public class SplashActivity extends AppCompatActivity {
    MediaPlayer mainMenuSong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        mainMenuSong = MediaPlayer.create(SplashActivity.this,R.raw.traffic_jam);
        mainMenuSong.start();

        Button exitGame = (Button) findViewById(R.id.exit_game_button);
        exitGame.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "TEST TEST MOTHER... oh, school project.", Toast.LENGTH_SHORT).show();
            }
        });
        Button newGame = (Button) findViewById(R.id.new_game_button);
        newGame.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "TEST TEST MOTHER... oh, school project.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
//im a comment
