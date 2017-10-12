package de.thorstenhack.moshiretrofittest

import android.app.Application
import android.util.Log
import kotlin.system.measureTimeMillis

/**
 * Created by thorstenhack on 12.10.17.
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        val timeMoshi = measureTimeMillis {
            ApiManagerMoshi.githubApi
        }
        Log.d("App", "init moshi took $timeMoshi ms")

        val timeGson = measureTimeMillis {
            ApiManagerGson.githubApi
        }
        Log.d("App", "init gson took $timeGson ms")
    }
}