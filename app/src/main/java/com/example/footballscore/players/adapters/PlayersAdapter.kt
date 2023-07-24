package com.example.footballscore.players.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.footballscore.R
import com.example.footballscore.players.data.entities.Player

class PlayersAdapter(private val listPlayers:List<Player>,
                     private val listener:PlayerComunicator)
    : RecyclerView.Adapter<PlayersViewHolder>(){


    interface PlayerComunicator{
        fun deletePlayer(position: Int, player: Player)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayersViewHolder {
        val lf = LayoutInflater.from(parent.context)
        return PlayersViewHolder(lf.inflate(R.layout.item_player,parent,false))
    }

    override fun onBindViewHolder(holder: PlayersViewHolder, position: Int) {
        val itemPlayer = listPlayers[position]
        holder.bind(itemPlayer,position,listener)
    }

    override fun getItemCount(): Int = listPlayers.size
}