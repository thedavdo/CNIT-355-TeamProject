package com.cnit.teamproject.android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.cnit.teamproject.R;

public class MainActivity extends AppCompatActivity {
    private static int SPLASHSCREENDELAY = 4000;//in MS clearly.
    Animation leftAnim, rightAnim;
    ImageView image;
    TextView gameName;
    MediaPlayer splashSong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        splashSong = MediaPlayer.create(MainActivity.this,R.raw.citycarhorn);
        splashSong.start();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent SplashIntent = new Intent(MainActivity.this, SplashActivity.class);
                startActivity(SplashIntent);
                finish();
            }
         },SPLASHSCREENDELAY);
        rightAnim = AnimationUtils.loadAnimation(this, R.anim.loading_animation_right);
        leftAnim = AnimationUtils.loadAnimation(this, R.anim.loading_animation_left);

        image = findViewById(R.id.imageView);
        gameName = findViewById(R.id.textView);
        image.setAnimation(rightAnim);
        gameName.setAnimation(leftAnim);
        //im a comment
        }
    }
