package com.example.footballscore.players.data.dao

import androidx.room.*
import com.example.footballscore.players.data.entities.Player

@Dao
interface PlayerDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlayer(player: Player)

    @Query("SELECT * FROM player ORDER BY nationality_player DESC")
    suspend fun getAllPlayers():List<Player>?

    @Query("SELECT * FROM player WHERE uid =:idPlayer")
    suspend fun getPlayerById(idPlayer:Int):Player

    @Delete
    suspend fun deletePlayer(player: Player):Int

    @Update
    suspend fun updatePlayer(player: Player):Int
}