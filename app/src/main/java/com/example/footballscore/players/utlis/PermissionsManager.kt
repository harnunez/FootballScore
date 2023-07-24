package com.example.footballscore.players.utlis

import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import java.util.jar.Manifest

class PermissionsManager{

    private val context:Context

    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>

    private var isCameraPermissionGranted=false
    private var isReadPermissionGranted=false
    private var isWritePermissionGranted=false


    constructor(parentActivity:AppCompatActivity){
        context= parentActivity
        initPermission(parentActivity)

    }

    private fun initPermission(parentActivity: AppCompatActivity) {

        permissionLauncher = parentActivity.
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()){ permission ->

            isCameraPermissionGranted = permission[android.Manifest.permission.CAMERA]?: isCameraPermissionGranted
            isReadPermissionGranted = permission[android.Manifest.permission.NFC]?: isReadPermissionGranted
            isWritePermissionGranted = permission[android.Manifest.permission.NFC]?: isWritePermissionGranted
        }

        requestPermission()
    }

    private fun requestPermission(){
        isCameraPermissionGranted = ContextCompat.checkSelfPermission(
            context, android.Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED

        isReadPermissionGranted = ContextCompat.checkSelfPermission(
            context, android.Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED

        isWritePermissionGranted = ContextCompat.checkSelfPermission(
            context, android.Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED

        val permissionsRequest: MutableList<String> = ArrayList()

        if(!isCameraPermissionGranted){
            permissionsRequest.add(android.Manifest.permission.CAMERA)
        }

        if(!isReadPermissionGranted){
            permissionsRequest.add(android.Manifest.permission.READ_EXTERNAL_STORAGE)
        }

        if(!isWritePermissionGranted){
            permissionsRequest.add(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }

        if(permissionsRequest.isNotEmpty()){
            permissionLauncher.launch(permissionsRequest.toTypedArray())
        }
    }



}