package com.example.footballscore.team.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.footballscore.R
import com.example.footballscore.databinding.FragmentCountriesBinding
import com.example.footballscore.team.CountriesAdapter
import com.example.footballscore.team.FootballScoreViewModel
import com.example.footballscore.team.model.Country


class CountriesFragment : Fragment() {

    private val viewModel: FootballScoreViewModel by activityViewModels()
    private lateinit var binding: FragmentCountriesBinding

    private lateinit var adapter: CountriesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCountriesBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()
    }

    private fun setupObserver(){

        viewModel.countries.observe(viewLifecycleOwner){ it?.let {  initRecycler(it) } }
    }


    private fun initRecycler(listCountry: List<Country>){
        adapter = CountriesAdapter(listCountry)
        binding.rvCountries.layoutManager = LinearLayoutManager(context)
        binding.rvCountries.adapter = adapter
    }


}