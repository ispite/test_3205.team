package ru.skillbox.test_3205team.ui.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.skillbox.test_3205team.data.NetworkRepository
import ru.skillbox.test_3205team.data.Repository

class ReposViewModel : ViewModel() {

    private val repository = NetworkRepository()

    private val _repoList = MutableLiveData<List<Repository>>()
    val repoList: LiveData<List<Repository>>
        get() = _repoList

    fun userRepos(owner: String) {
        viewModelScope.launch {
            _repoList.postValue(repository.userRepos(owner))
        }
    }
}