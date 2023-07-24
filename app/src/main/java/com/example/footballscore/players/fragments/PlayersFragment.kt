package com.example.footballscore.players.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.footballscore.databinding.FragmentPlayersBinding
import com.example.footballscore.players.PlayersViewModel
import com.example.footballscore.players.adapters.PlayersAdapter
import com.example.footballscore.players.data.entities.Player

class PlayersFragment : Fragment() {

    private lateinit var binding: FragmentPlayersBinding
    private val viewModel: PlayersViewModel by activityViewModels()
    private lateinit var playersAdapter: PlayersAdapter

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
        viewModel.getAllPlayers()
        setupObservers()
    }

    private fun setupObservers(){
        viewModel.playersRoom.observe(viewLifecycleOwner){
            Log.d("Lista","PLAYERS")
            it?.let { it ->
                it.forEach { p->
                    println(p.firstName+" "+p.age+" "+p.nationality+" "+p.position+" "+p.uid)
                }
            }

            it?.let { initRecycler(it) }
        }

        //Testear busqueda por id
        viewModel.playerById.observe(viewLifecycleOwner){
            it?.let { println(it.firstName+" "+it.nationality +" "+it.uid) }
        }

        viewModel.playerEliminated.observe(viewLifecycleOwner){
            it?.let { println(it.toString())}
        }

        viewModel.playerToUpdate.observe(viewLifecycleOwner){
            Log.d("PLayer",it.toString())
            it?.let { println(it.toString())}
        }

    }

    private fun setupViews(){

        binding.btnSave.setOnClickListener {
            Log.d("CLICK"," SAVE")

            val playerToSave = Player( firstName = binding.name.text.toString(),
                age = binding.age.text.toString(),
                nationality = binding.nacionality.text.toString(),
                position = binding.position.text.toString())

            viewModel.savePlayerDB(playerToSave)
        }

        binding.btnAddPlayer.apply {
            setOnClickListener {
                visibility = View.GONE
                binding.linearOne.visibility = View.VISIBLE

            }
        }

        binding.btnUp.apply {
            setOnClickListener{
                binding.linearOne.visibility = View.GONE
                binding.btnAddPlayer.visibility = View.VISIBLE
            }
        }


        //binding.btnGetPlayers.setOnClickListener { viewModel.getAllPlayers() }

        // Crear metodo getPlayerById (Search player by Id)
        /*binding.btnGetById.setOnClickListener {

            val id = binding.idPlayer.text.toString()
            viewModel.searchById(id.toInt())
        }*/

        //crear metodo updatePLayer(Se va usar)
        /*binding.btnUpdate.setOnClickListener {

            val playerToUpdate = Player(uid = binding.idPlayer.text.toString().toInt() ,
                firstName = binding.name.text.toString(), age = binding.age.text.toString(),
                nationality = binding.nacionality.text.toString(),
                position = binding.position.text.toString())

            viewModel.updatePlayer(playerToUpdate)
        }*/
    }


    private fun initRecycler(listPLayers:List<Player>){

        playersAdapter = PlayersAdapter(listPLayers,
            object : PlayersAdapter.PlayerComunicator {
                override fun deletePlayer(position:Int, player:Player) {
                    viewModel.deletePlayer(player)
                }

            })

        binding.rvPlayers.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = playersAdapter
        }
    }



}