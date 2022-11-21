package ru.skillbox.test_3205team.data

import ru.skillbox.test_3205team.network.Networking

class NetworkRepository {

    suspend fun searchUsers(query: String) = Networking.gitHubApi.searchUsers(query).items

    suspend fun userRepos(owner: String) = Networking.gitHubApi.userRepos(owner)

/*    suspend fun downloadMasterRepo(owner: String, repo: String) =
        Networking.gitHubApi.downloadMasterRepo(owner, repo)*/
}
