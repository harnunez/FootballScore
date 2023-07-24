package com.example.footballscore.core.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.footballscore.databinding.ActivitySplashBinding
import com.example.footballscore.players.activities.ContainerPlayersActivity
import com.example.footballscore.team.ContainerActivity
import java.text.NumberFormat
import java.util.*

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private var hello = "Holi"

    override fun onCreate(savedInstanceState: Bundle?) {
        val screenSplash = installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Thread.sleep(2000)

        /**
         * Implementar viewTreeObsever cuando haya viewModel y DDBB
         */
        screenSplash.setKeepOnScreenCondition { false }
        navigateToCountries()
        numberFormat()
    }

    override fun onStart() {
        super.onStart()
        Log.d("TESTHOLI", hello)
    }


    private fun navigateToCountries(){
        binding.btnGameResults.setOnClickListener {
            val intent = Intent(this, ContainerActivity::class.java)
            startActivity(intent)
        }

        binding.btnScoringPlayers.setOnClickListener {
            hello="perrito"
            val intent = Intent(this, ContainerPlayersActivity::class.java)
            startActivity(intent)
        }
    }

    private fun numberFormat(){//Esta es una prueba galicia
        val dbl = 1050088888.3245
        val formatter: NumberFormat = NumberFormat.getCurrencyInstance(Locale.US)
        val currency: String = formatter.format(dbl)
        println("$currency for the locale")
        println(Locale.getDefault().country +" for the locale")
    }
}