package ru.skillbox.test_3205team.data

import ru.skillbox.test_3205team.network.Networking

class MainRepository {

    suspend fun searchUsers(query: String) =
//        Timber.d("searchUsers: $query")
        Networking.gitHubApi.searchUsers(query).items
}