package com.example.footballscore.core.db

import android.content.Context
import androidx.room.Room
import com.example.footballscore.players.data.PlayerDB
import com.example.footballscore.players.data.dao.PlayerDao

object RoomDBSingleton {

    private lateinit var roomDB :  PlayerDB

    fun getRoom(context: Context){
         roomDB = Room.databaseBuilder(context , PlayerDB::class.java,"footballScore")
            .build()
    }

    fun getRoomDao(): PlayerDao{
        return roomDB.playerDao()
    }
}