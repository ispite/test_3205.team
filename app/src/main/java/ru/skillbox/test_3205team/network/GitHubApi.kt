package ru.skillbox.test_3205team.network

import retrofit2.http.GET
import retrofit2.http.Query
import ru.skillbox.test_3205team.data.User

interface GitHubApi {

    @GET("search/users")
    suspend fun searchUsers(
        @Query("q") query: String
    ): ItemsWrapper<User>
}