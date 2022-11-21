package ru.skillbox.test_3205team.ui.repo

import android.app.Application
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.skillbox.test_3205team.data.NetworkRepository
import ru.skillbox.test_3205team.data.RepoRepository
import ru.skillbox.test_3205team.data.Repository
import timber.log.Timber

class ReposViewModel(app: Application) : AndroidViewModel(app) {

    private val repository = NetworkRepository()
    private val repoRepository = RepoRepository(app)

    private val _repoList = MutableLiveData<List<Repository>>()
    val repoList: LiveData<List<Repository>>
        get() = _repoList

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean>
        get() = _loading

    fun userRepos(owner: String) {
        viewModelScope.launch {
            _repoList.postValue(repository.userRepos(owner))
        }
    }

    fun downloadRepoZip(uri: Uri, owner: String, repo: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _loading.postValue(true)
            try {
                repoRepository.downloadRepoZip(uri, owner, repo)
            } catch (t: Throwable) {
                Timber.e(t)
            } finally {
                _loading.postValue(false)
            }
        }
    }
}
