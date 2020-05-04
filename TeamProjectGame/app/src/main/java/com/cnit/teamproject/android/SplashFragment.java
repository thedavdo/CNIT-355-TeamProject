package com.cnit.teamproject.android;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.cnit.teamproject.R;

public class SplashFragment extends Fragment {



    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_splash, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MediaPlayer splashSong = MediaPlayer.create(getContext(), R.raw.citycarhorn);
        splashSong.start();

        Animation rightAnim = AnimationUtils.loadAnimation(getContext(), R.anim.loading_animation_right);
        Animation leftAnim = AnimationUtils.loadAnimation(getContext(), R.anim.loading_animation_left);
        Animation finalAnim = AnimationUtils.loadAnimation(getContext(), R.anim.loading_animation_final);

        ImageView image1Right = view.findViewById(R.id.merge1);
        ImageView image1Left =  view.findViewById(R.id.merge2);
        ImageView image2Center =  view.findViewById(R.id.mergefinal);

        image1Right.setAnimation(rightAnim);
        image1Left.setAnimation(leftAnim);
        image2Center.setAnimation(finalAnim);
    }
}
