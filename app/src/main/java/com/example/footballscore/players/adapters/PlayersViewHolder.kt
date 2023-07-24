package com.example.footballscore.players.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.footballscore.databinding.ItemPlayerBinding
import com.example.footballscore.players.data.entities.Player

class PlayersViewHolder(view: View): RecyclerView.ViewHolder(view) {

   private val binding =  ItemPlayerBinding.bind(view)

    fun bind(player: Player,
             position:Int,
             listener: PlayersAdapter.PlayerComunicator){

        binding.apply {
            idIdentifier.text = player.uid.toString()
            idName.text = player.firstName
            idAge.text = player.age
            idNationality.text = player.nationality
            idPosition.text = player.position

            idDeletePlayer.setOnClickListener { listener.deletePlayer(position,player) }
        }
    }
}