package com.example.footballscore.players.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Player (

    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    @ColumnInfo(name = "name_player") val firstName: String?,
    @ColumnInfo(name = "age_player") val age: String?,
    @ColumnInfo(name = "nationality_player") val nationality: String?,
    @ColumnInfo(name = "position_player") val position: String?
)