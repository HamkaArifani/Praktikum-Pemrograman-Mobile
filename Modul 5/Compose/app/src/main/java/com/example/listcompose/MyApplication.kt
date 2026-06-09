package com.example.listcompose

import android.app.Application
import android.content.pm.ApplicationInfo
import timber.log.Timber
import com.example.listcompose.R

class MyApplication : Application() {
    companion object {
        var isDebug: Boolean = false
        var tmdbApiKey : String = ""
    }
    override fun onCreate() {
        super.onCreate()
        isDebug = (applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE) != 0
        if (isDebug){
            Timber.plant(Timber.DebugTree())
        }

        tmdbApiKey = getString(R.string.TMDB_API_KEY)
    }
}