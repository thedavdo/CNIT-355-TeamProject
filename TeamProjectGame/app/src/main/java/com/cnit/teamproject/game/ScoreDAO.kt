package com.cnit.teamproject.game

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ScoreDAO {

    @Query("SELECT * FROM score ORDER BY score.score DESC")
    fun getScores(): LiveData<List<Score>>

    @Query("SELECT *, MAX(score.score) FROM score")
    fun getHighScores(): LiveData<List<Score>>

    @Insert
    fun addScore(score : Score)
}