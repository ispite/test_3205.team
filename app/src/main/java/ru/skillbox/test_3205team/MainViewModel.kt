package ru.skillbox.test_3205team

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import ru.skillbox.test_3205team.data.MainRepository
import ru.skillbox.test_3205team.data.User

class MainViewModel : ViewModel() {

    private val repository = MainRepository()
    lateinit var job: Job

    private val _userList = MutableLiveData<List<User>>()
    val userList: LiveData<List<User>>
        get() = _userList

    fun search(queryFlow: Flow<String>) {
//        Timber.d("qweqe")
        job = queryFlow.debounce(500)
            .distinctUntilChanged()
            .mapLatest { query ->
//                val list = repository.searchUsers(query)
                _userList.postValue(repository.searchUsers(query))
            }
            .launchIn(viewModelScope)

    }
}