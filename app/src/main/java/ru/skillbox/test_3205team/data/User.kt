package ru.skillbox.test_3205team.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class User(
    @Json(name = "login")
    val username: String,

    @Json(name = "avatar_url")
    val avatar: String?,

    val id: Long
)
