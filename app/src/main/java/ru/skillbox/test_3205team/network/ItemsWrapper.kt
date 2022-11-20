package ru.skillbox.test_3205team.network

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ItemsWrapper<T>(
    val items: List<T>
)
