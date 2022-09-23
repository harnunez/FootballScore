package com.example.footballscore.team

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.footballscore.NavigationModule
import com.example.footballscore.databinding.ActivityContainerBinding
import com.example.footballscore.team.fragments.CountriesFragment

class ContainerActivity : AppCompatActivity() {


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

    }

    private fun navigate(navigationModule: NavigationModule){

        when(navigationModule){
            NavigationModule.COUNTRIES_SCREEN -> {
                goToFragment(CountriesFragment())
            }
        }
    }

    private fun goToFragment(fragment: Fragment) {
        fragmentmanager.beginTransaction()
            .replace(binding.container.id, fragment)
            .commit()
    }

}