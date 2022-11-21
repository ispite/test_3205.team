package ru.skillbox.test_3205team.data

import android.content.Context
import android.net.Uri
import ru.skillbox.test_3205team.network.Networking
import timber.log.Timber

class RepoRepository(private val context: Context) {

    suspend fun downloadRepoZip(
        uri: Uri,
        owner: String,
        repo: String
    ) {
        try {
            context.contentResolver.openOutputStream(uri)?.buffered()?.use { outputStream ->
                Networking.gitHubApi.downloadMasterRepo(owner, repo).byteStream()
                    .use { inputStream ->
                        Timber.d("Started copying")
                        inputStream.copyTo(outputStream)
                    }
            }
        } catch (t: Throwable) {
            Timber.e(t)
        }
    }
}
