package com.example.footballscore

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.footballscore.databinding.ItemCountryBinding
import com.example.footballscore.model.Country
import com.example.footballscore.model.ResponseCountries
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class CountriesViewHolder(view:View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemCountryBinding.bind(view)

    fun bind(country : Country){
        binding.apply {
            val img = country.flag
            //val img = "https://cdn.maikoapp.com/3d4b/4quqa/150.jpg"
            Picasso.get().load(img).into(idFlagCountry)
            idNameCountry.text = country.name

        }
    }

}