package com.example.footballscore.players.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.footballscore.databinding.ActivityContainerPlayersBinding
import com.example.footballscore.players.NavigationPlayers
import com.example.footballscore.players.PlayersViewModel
import com.example.footballscore.players.fragments.PlayersFragment
import com.example.footballscore.players.utlis.PermissionsManager

class ContainerPlayersActivity : AppCompatActivity() {

    private val viewModel: PlayersViewModel by viewModels()
    private val fragmentmanager: FragmentManager by lazy { supportFragmentManager }
    private lateinit var binding: ActivityContainerPlayersBinding
    private var permission:PermissionsManager? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityContainerPlayersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        permission = PermissionsManager(this)

        viewModel.init()
        setupObserver()
    }

    private fun setupObserver(){
        viewModel.navigation.observe(this){
            it?.let { navigate(it) }
        }
    }


    private fun navigate(navigationModule: NavigationPlayers){
        when(navigationModule){
            NavigationPlayers.INSERT_PLAYER -> {
                goToFragment(PlayersFragment())
            }
        }
    }

    private fun goToFragment(fragment: Fragment) {
        fragmentmanager.beginTransaction()
            .replace(binding.containerPlayers.id, fragment)
            .commit()
    }
}