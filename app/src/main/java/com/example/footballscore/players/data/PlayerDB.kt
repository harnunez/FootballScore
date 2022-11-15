package com.example.footballscore.players.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.footballscore.players.data.dao.PlayerDao
import com.example.footballscore.players.data.entities.Player

@Database(
    entities = [Player::class],
    version = 1
)
abstract class PlayerDB : RoomDatabase(){

    abstract fun playerDao(): PlayerDao

}