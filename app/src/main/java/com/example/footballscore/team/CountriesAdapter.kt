package com.example.footballscore.team

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.footballscore.R
import com.example.footballscore.team.model.Country

class CountriesAdapter(private val listCountries:List<Country>) :
    RecyclerView.Adapter<CountriesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountriesViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        return CountriesViewHolder(layoutInflater.inflate(R.layout.item_country, parent,false))
    }

    override fun getItemCount(): Int = listCountries.size

    override fun onBindViewHolder(holder: CountriesViewHolder, position: Int) {
        val itemCountry = listCountries[position]
        holder.bind(itemCountry)
    }

}