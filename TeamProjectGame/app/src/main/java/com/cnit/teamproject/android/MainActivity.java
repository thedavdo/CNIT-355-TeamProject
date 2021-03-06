package com.cnit.teamproject.android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
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

public class MainActivity extends AppCompatActivity implements MainMenuFragment.Callbacks {


    private static int SPLASHSCREENDELAY = 4000; //in MS clearly.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Fragment frag = getSupportFragmentManager().findFragmentById(R.id.fragment_container);

        if(savedInstanceState != null) {
            //TODO
        }
        else if(frag == null) {

            frag = new SplashFragment();

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, frag)
                    .commit();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    MainMenuFragment mmFrag = new MainMenuFragment();

                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container, mmFrag)
                            .commit();

                }
            }, SPLASHSCREENDELAY);
        }
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    public void onScoresPress() {

        ScoreListFragment scoreFrag = new ScoreListFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, scoreFrag)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onStartPress() {

        GameFragment gameFrag = new GameFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, gameFrag)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onBackPressed() {

        Fragment fr = getSupportFragmentManager().findFragmentById(R.id.fragment_container);

        boolean consumed = false;

        if(fr instanceof BackPressedListener) {
            consumed = ((BackPressedListener) fr).onBackPressed();
        }

        if(!consumed) super.onBackPressed();
    }

    @Override
    public void onExitPress() {

        finish();
    }
}
