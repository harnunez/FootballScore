package com.example.footballscore.team

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.footballscore.team.repository.FootballSoccerRepository

class FootballScoreVMFactory(
    private val repository: FootballSoccerRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>)
        = FootballScoreViewModel(repository) as T
}