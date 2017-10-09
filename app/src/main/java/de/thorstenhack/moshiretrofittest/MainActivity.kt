package de.thorstenhack.moshiretrofittest

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_moshi.setOnClickListener {
            val timeMillis = ApiManagerMoshi.execute(object : Callback<List<GithubRepoResponse>> {
                override fun onResponse(call: Call<List<GithubRepoResponse>>, response: Response<List<GithubRepoResponse>>) {
                   log.text = "${log.text}got ${response.body()?.size ?: "no"} repos\n"
                }

                override fun onFailure(call: Call<List<GithubRepoResponse>>, t: Throwable?) {
                    log.text = "${log.text}getting repos failed\n"
                }

            })
            log.text = "${log.text}execute with moshi returned after $timeMillis ms\n"
        }

        button_gson.setOnClickListener {
            val timeMillis = ApiManagerGson.execute(object : Callback<List<GithubRepoResponse>> {
                override fun onResponse(call: Call<List<GithubRepoResponse>>, response: Response<List<GithubRepoResponse>>) {
                    log.text = "${log.text}got ${response.body()?.size ?: "no"} repos\n"
                }

                override fun onFailure(call: Call<List<GithubRepoResponse>>, t: Throwable?) {
                    log.text = "${log.text}getting repos failed\n"
                }

            })
            log.text = "${log.text}execute with gson returned after $timeMillis ms\n"
        }
    }

}
