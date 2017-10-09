package de.thorstenhack.moshiretrofittest

import android.util.Log
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import kotlin.system.measureTimeMillis

/**
 * Created by thorstenhack on 09.10.17.
 */
object ApiManagerMoshi {

    private val BASE_URL = "https://api.github.com/"

    private val TAG = "ApiManagerMoshi"

    private val githubApi by lazy {
        val loggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { Log.d(TAG, it) })
        loggingInterceptor.level = HttpLoggingInterceptor.Level.HEADERS

        val client = OkHttpClient
                .Builder()
                .addInterceptor(loggingInterceptor)
                .build()

        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().add(KotlinJsonAdapterFactory()).build()))
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