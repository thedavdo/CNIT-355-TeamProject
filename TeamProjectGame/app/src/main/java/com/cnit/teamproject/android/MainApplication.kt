package com.cnit.teamproject.android

import android.app.Application
import com.cnit.teamproject.game.ScoreRepository

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        ScoreRepository.initialize(this)
    }
}