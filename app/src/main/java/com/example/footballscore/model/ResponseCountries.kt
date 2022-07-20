package com.example.footballscore.model

import com.google.gson.annotations.SerializedName

data class ResponseCountries (
     var response:List<Country>,
    var results:Int)