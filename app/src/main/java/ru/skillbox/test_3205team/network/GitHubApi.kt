package ru.skillbox.test_3205team.network

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.skillbox.test_3205team.data.Repository
import ru.skillbox.test_3205team.data.User

interface GitHubApi {

    @GET("search/users")
    suspend fun searchUsers(
        @Query("q") query: String
    ): ItemsWrapper<User>

    @GET("/users/{owner}/repos")
    suspend fun userRepos(
        @Path("owner") owner: String
    ): List<Repository>

    @GET("/repos/{owner}/{repo}/zipball/")
    suspend fun downloadMasterRepo(
        @Path("owner") owner: String,
        @Path("repo") repo: String
    ): ResponseBody
}
