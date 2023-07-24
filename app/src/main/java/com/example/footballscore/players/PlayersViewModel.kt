package com.example.footballscore.players

import android.util.Log
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

    //Get ROOM DATA:
    //Players
    private val _playersRoom = MutableLiveData<List<Player>?>()
    val playersRoom : LiveData<List<Player>?> = _playersRoom

    //Search by id
    private val _playerById = MutableLiveData<Player?>()
    val playerById : LiveData<Player?> = _playerById

    //Delete Player
    private val _playerDeleted = MutableLiveData<Int?>()
    val playerEliminated: LiveData<Int?> = _playerDeleted

    // Update Player
    private val _playerToUpdate = MutableLiveData<Int?>()
    val playerToUpdate: LiveData<Int?> = _playerToUpdate


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

    fun searchById(id_player: Int){
        viewModelScope.launch {
            _playerById.postValue(roomSingleton.getPlayerById(id_player))
        }
    }

    fun deletePlayer(player: Player){
        viewModelScope.launch {
            val count = roomSingleton.deletePlayer(player)
            Log.d("PLayerVVMM",count.toString())
            count.let {
                if(it>0){
                    _playerDeleted.postValue(it)
                    getAllPlayers()
                }
            }
        }
    }

    fun updatePlayer(player: Player){
        viewModelScope.launch {
            _playerToUpdate.postValue(roomSingleton.updatePlayer(player))
        }
    }
}