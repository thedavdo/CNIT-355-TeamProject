package com.cnit.teamproject.game

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Score::class], version=1)
@TypeConverters(ScoreTypeConverters::class)
abstract class ScoreDatabase : RoomDatabase() {

    abstract fun scoreDAO(): ScoreDAO
}