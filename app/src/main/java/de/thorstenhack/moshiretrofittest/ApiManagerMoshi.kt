package de.thorstenhack.moshiretrofittest

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Callback
import retrofit2.Retrofit
import kotlin.system.measureTimeMillis

/**
 * Created by thorstenhack on 09.10.17.
 */
object ApiManagerMoshi {

    private val BASE_URL = "https://api.github.com/"

    private val TAG = "ApiManagerMoshi"

    val githubApi by lazy {
        val loggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { Log.d(TAG, it) })
        loggingInterceptor.level = HttpLoggingInterceptor.Level.HEADERS

        val client = OkHttpClient
                .Builder()
                .addInterceptor(loggingInterceptor)
                .build()

        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ApplicationJsonAdapterFactory.INSTANCE)
                .client(client)
                .build()

        retrofit.create(GithubApi::class.java)
    }


    fun execute(callback: Callback<List<GithubRepoResponse>>): Long {
        return measureTimeMillis {
            val call = githubApi.getUserRepos("Phoca")
            call.enqueue(callback)
        }
    }
}