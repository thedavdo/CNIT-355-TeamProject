package com.cnit.teamproject.game

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import java.util.concurrent.Executors

private const val DATABASE_NAME = "scores-multiplicity"

class ScoreRepository private constructor(context: Context) {

    private val database: ScoreDatabase = Room.databaseBuilder(
            context.applicationContext,
            ScoreDatabase::class.java,
            DATABASE_NAME
    ).build()

    private val scoreDAO = database.scoreDAO()
    private val executor = Executors.newSingleThreadExecutor()

    fun getScores(): LiveData<List<Score>> = scoreDAO.getScores()

    fun getHighscores(): LiveData<List<Score>> = scoreDAO.getHighScores()

    fun addScore(score: Score) {
        executor.execute {
            scoreDAO.addScore(score)
        }
    }

    //Singleton :(
    companion object {

        private var INSTANCE: ScoreRepository? = null

        fun initialize(context: Context) {
            if(INSTANCE == null) {
                INSTANCE = ScoreRepository(context)
            }
        }

        fun get(): ScoreRepository {
            return INSTANCE?: throw IllegalStateException("ScoreRepository must be initialized")
        }
    }
}