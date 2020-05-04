package com.cnit.teamproject.android

import androidx.lifecycle.ViewModel
import com.cnit.teamproject.game.Score
import com.cnit.teamproject.game.ScoreRepository

class ScoreListViewModel : ViewModel() {

    private val scoreRepository = ScoreRepository.get()
    val scoreListLiveData = scoreRepository.getScores()
    val highscoreListLiveData = scoreRepository.getHighscores()

    fun addScore(score: Score) {
        scoreRepository.addScore(score)
    }
}