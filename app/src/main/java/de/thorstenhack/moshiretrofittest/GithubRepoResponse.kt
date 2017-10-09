package de.thorstenhack.moshiretrofittest

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json

/**
 * Created by thorstenhack on 09.10.17.
 */

data class GithubRepoResponse(
        val id: Long,
        val name: String,
        @SerializedName("login") @Json(name = "full_name") val fullName: String,
        val owner: Owner,
        val private: Boolean
)

data class Owner (
        val id: Long,
        @SerializedName("login") @Json(name = "login") val username: String,
        val url: String
)