package com.example.footballscore.players

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PlayersViewModel : ViewModel() {

    private val _navigation = MutableLiveData<NavigationPlayers>()
    val navigation : LiveData<NavigationPlayers?> = _navigation

    fun init() {
        setNavigate(NavigationPlayers.INSERT_PLAYER)
    }


    fun setNavigate(navigation: NavigationPlayers){
        _navigation.postValue(navigation)
    }
}