package de.thorstenhack.moshiretrofittest

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by thorstenhack on 09.10.17.
 */

interface GithubApi {

    @GET("users/{user}/repos")
    fun getUserRepos(
            @Path("user") user: String
    ): Call<List<GithubRepoResponse>>
}