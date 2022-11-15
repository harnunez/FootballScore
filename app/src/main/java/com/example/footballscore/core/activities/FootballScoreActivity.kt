package com.example.footballscore.core.activities

import android.app.Dialog
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.footballscore.databinding.ProgressBarGeneralBinding

abstract class FootballScoreActivity : AppCompatActivity(){

    private var alert: Dialog? = null



    fun startProgressDialog() {
        alert = Dialog(this, android.R.style.Theme_Material_Light_NoActionBar_Fullscreen)
        val view = ProgressBarGeneralBinding.inflate(layoutInflater).root
        alert!!.setContentView(view)

        alert!!.setCancelable(false)
        alert!!.show()

    }

    fun stopProgressDialog(){
        Log.d("MAIN", "Stop progress")
        if (alert != null && alert!!.isShowing) {
            Log.d("MAIN", "Stop22 progress")
            alert!!.dismiss()
        }
    }
}