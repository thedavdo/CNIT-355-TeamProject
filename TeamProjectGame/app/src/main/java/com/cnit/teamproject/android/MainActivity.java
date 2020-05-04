package com.cnit.teamproject.android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cnit.teamproject.R;

public class MainActivity extends AppCompatActivity {

    private static int SPLASHSCREENDELAY = 4000; //in MS clearly.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Fragment frag = getSupportFragmentManager().findFragmentById(R.id.fragment_container);

        if(frag == null) {
            frag = new SplashFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, frag).commit();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    MainMenuFragment mmFrag = new MainMenuFragment();
                    getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, mmFrag).commit();
                }
            }, SPLASHSCREENDELAY);
        }
    }
}
