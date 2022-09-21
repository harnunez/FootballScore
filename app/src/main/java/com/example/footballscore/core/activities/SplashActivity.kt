package com.example.footballscore.core.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.footballscore.CountriesActivity
import com.example.footballscore.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

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
    }


    private fun navigateToCountries(){
        binding.btnGameResults.setOnClickListener {
            val intent = Intent(this, CountriesActivity::class.java)
            startActivity(intent)
        }
    }
}