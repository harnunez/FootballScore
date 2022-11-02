package com.example.footballscore.team.fragments

import android.R
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.footballscore.team.utils.NavigationModule
import com.example.footballscore.databinding.FragmentCountriesBinding
import com.example.footballscore.team.adapter.CountriesAdapter
import com.example.footballscore.team.FootballScoreViewModel
import com.example.footballscore.team.model.Country


class CountriesFragment : Fragment() {

    private val viewModel: FootballScoreViewModel by activityViewModels()
    private lateinit var binding: FragmentCountriesBinding

    private lateinit var adapter: CountriesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCountriesBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupviews()
        setupObserver()
    }

    private fun setupObserver(){

        viewModel.countries.observe(viewLifecycleOwner){ it.let {  initRecycler(it) } }
        viewModel.seasons.observe(viewLifecycleOwner){it.let { fillSpinner(it) }}
    }

    private fun fillSpinner(seasons:List<Int>){
        Log.d("Fill Spinner","method")

        val newSeasons= seasons.sortedDescending().reversed()
        if(!newSeasons.isNullOrEmpty()) viewModel.setSeasonSelected(newSeasons[0])
        binding.spinnerSeasson.adapter = setAdapter(newSeasons)
        setClickSpinner(newSeasons)
    }

    private fun setAdapter(seasons:List<Int>):ArrayAdapter<Int>{
        Log.d("Adapter","method")
        val adapter: ArrayAdapter<Int> =
            ArrayAdapter(binding.root.context, R.layout.simple_spinner_dropdown_item, seasons)
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)

        return adapter
    }

    private fun setupviews(){
        binding.btnSeason.setOnClickListener { viewModel.searchSessions() }
    }

    private fun setClickSpinner(seasonList:List<Int>){
        binding.spinnerSeasson.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                viewModel.setSeasonSelected(seasonList[position])
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
    }

    private fun initRecycler(listCountry: List<Country>){
        adapter = CountriesAdapter(listCountry,
            object : CountriesAdapter.RecyclerCountriesClickListener {
                override fun goToLeague(country: Country, position: Int) {
                    viewModel.setNavigate(NavigationModule.LEAGUE_SCREEN)
                }
            })

        binding.rvCountries.layoutManager = LinearLayoutManager(context)
        binding.rvCountries.adapter = adapter
    }


}