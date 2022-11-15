package com.example.footballscore.players

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.footballscore.core.db.RoomDBSingleton
import com.example.footballscore.players.data.dao.PlayerDao
import com.example.footballscore.players.data.entities.Player
import kotlinx.coroutines.launch

class PlayersViewModel : ViewModel() {

    private val _navigation = MutableLiveData<NavigationPlayers>()
    val navigation : LiveData<NavigationPlayers?> = _navigation
    private val roomSingleton:PlayerDao by lazy { RoomDBSingleton.getRoomDao() }

    //Get ROOM DATA
    private val _playersRoom = MutableLiveData<List<Player>?>()
    val playersRoom : LiveData<List<Player>?> = _playersRoom



    fun init() {
        setNavigate(NavigationPlayers.INSERT_PLAYER)
    }


    fun setNavigate(navigation: NavigationPlayers){
        _navigation.postValue(navigation)
    }

    fun savePlayerDB(player: Player){

        viewModelScope.launch {
            roomSingleton.insertPlayer(player)
        }

    }

    fun getAllPlayers(){
        viewModelScope.launch {
             _playersRoom.postValue(roomSingleton.getAllPlayers())
        }
    }
}