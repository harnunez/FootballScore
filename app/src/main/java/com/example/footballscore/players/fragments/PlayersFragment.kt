package com.example.footballscore.players.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.footballscore.databinding.FragmentPlayersBinding
import com.example.footballscore.players.PlayersViewModel
import com.example.footballscore.players.data.entities.Player

class PlayersFragment : Fragment() {

    private lateinit var binding: FragmentPlayersBinding
    private val viewModel: PlayersViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentPlayersBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        setupObservers()
    }

    private fun setupObservers(){
        viewModel.playersRoom.observe(viewLifecycleOwner){
            Log.d("Lista","PLAYERS")
            it?.let { it ->
                it.forEach { p->
                    println(p.firstName+" "+p.age+" "+p.nationality+" "+p.position+""+p.uid)
                }
            }
        }
    }

    private fun setupViews(){

        binding.btnSave.setOnClickListener {
            Log.d("CLICK"," SAVE")

            val playerToSave = Player( firstName = binding.name.text.toString(), age = binding.age.text.toString(),
                               nationality = binding.nacionality.text.toString(),
                                position = binding.position.text.toString())


            viewModel.savePlayerDB(playerToSave)
        }

        binding.btnGetPlayers.setOnClickListener { viewModel.getAllPlayers() }
    }

}