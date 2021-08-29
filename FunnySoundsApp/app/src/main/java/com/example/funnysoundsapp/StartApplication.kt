package com.example.funnysoundsapp

import android.app.Application
import android.os.Bundle
import com.parse.Parse
import com.parse.ParseACL

class StartApplication : Application()
{
    fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate()
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
}