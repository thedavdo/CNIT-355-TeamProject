package com.cnit.teamproject.game

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Score(@PrimaryKey var uuid: UUID) {

    var name : String = "<unset>"
    var score : Int = -1
}