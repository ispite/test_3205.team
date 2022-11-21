package ru.skillbox.test_3205team.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Repository(
    val id: Long,
    val name: String,
    val owner: User,
    val description: String?
)
