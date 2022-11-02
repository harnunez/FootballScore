package com.example.footballscore.team

import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.footballscore.team.utils.NavigationModule
import com.example.footballscore.core.activities.FootballScoreActivity
import com.example.footballscore.databinding.ActivityContainerBinding
import com.example.footballscore.team.fragments.CountriesFragment
import com.example.footballscore.team.fragments.LeagueFragment

class ContainerActivity : FootballScoreActivity() {


    private val viewModel: FootballScoreViewModel by viewModels()
    private val fragmentmanager: FragmentManager by lazy { supportFragmentManager }
    private lateinit var binding: ActivityContainerBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContainerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.init()
        setupObserver()

    }

    private fun setupObserver(){

        viewModel.navigation.observe(this){
            if(it != null) navigate(it)
        }

        viewModel.isLoading.observe(this){
            if(it) startProgressDialog() else stopProgressDialog()
        }

    }

    private fun navigate(navigationModule: NavigationModule){

        when(navigationModule){
            NavigationModule.COUNTRIES_SCREEN -> {
                goToFragment(CountriesFragment())
            }

            NavigationModule.LEAGUE_SCREEN ->{
                goToFragment(LeagueFragment())
            }
        }
    }

    private fun goToFragment(fragment: Fragment) {
        fragmentmanager.beginTransaction()
            .replace(binding.container.id, fragment)
            .commit()
    }

    override fun onBackPressed() {
        when(viewModel.navigation.value){
            NavigationModule.LEAGUE_SCREEN ->{
                viewModel.setNavigate(NavigationModule.COUNTRIES_SCREEN)
            }

            NavigationModule.COUNTRIES_SCREEN -> {
                finish()
            }
            else -> {}
        }
    }

}