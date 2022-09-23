package com.example.footballscore.team

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.footballscore.databinding.ItemCountryBinding
import com.example.footballscore.team.model.Country

class CountriesViewHolder(view:View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemCountryBinding.bind(view)

    fun bind(country : Country){
        binding.apply {
            val img = country.flag

            //val img = "https://cdn.maikoapp.com/3d4b/4quqa/150.jpg"
            //Picasso.get().load(img).into(idFlagCountry) --- Solucionar el parseo de imagenes a JPG

            idNameCountry.text = country.name

        }
    }

}