package com.example.footballscore.core

import android.app.Application
import com.example.footballscore.core.db.RoomDBSingleton

class FootballScoreApplication : Application(){

    override fun onCreate() {
        super.onCreate()

        RoomDBSingleton.getRoom(this)
    }
}