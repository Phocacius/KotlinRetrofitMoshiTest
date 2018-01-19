package de.thorstenhack.moshiretrofittest

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import se.ansman.kotshi.JsonSerializable

/**
 * Created by thorstenhack on 09.10.17.
 */

@JsonSerializable
data class GithubRepoResponse(
        val id: Long,
        val name: String,
        @SerializedName("full_name") @Json(name = "full_name") val fullName: String,
        val owner: Owner,
        @SerializedName("private") @Json(name = "private") val isPrivate: Boolean
)

@JsonSerializable
data class Owner(
        val id: Long,
        @SerializedName("login") @Json(name = "login") val username: String,
        val url: String
)