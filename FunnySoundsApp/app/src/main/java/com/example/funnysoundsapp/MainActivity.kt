package com.example.funnysoundsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.Navigation
import com.parse.Parse
import com.parse.ParseACL
import com.parse.ParseObject

class MainActivity : AppCompatActivity()
{
    //https://www.back4app.com/docs/android/login-android-tutorial
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Parse.initialize(
            Parse.Configuration.Builder(this)
                .applicationId(getString(R.string.back4app_app_id))
                .clientKey(getString(R.string.back4app_client_key))
                .server(getString(R.string.back4app_server_url))
                .build())

        val defaultACL = ParseACL()
        defaultACL.setPublicReadAccess(true)
        defaultACL.setPublicWriteAccess(true)
        ParseACL.setDefaultACL(defaultACL, true)
    }

    override fun onSupportNavigateUp(): Boolean
    {
        return Navigation.findNavController(this, R.id.nav_host_fragment).navigateUp()
    }
}