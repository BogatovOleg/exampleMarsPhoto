package com.example.fragmentsnavigation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fragmentsnavigation.network.GitApi
import kotlinx.coroutines.launch
import java.util.*

enum class MarsApiStatus { LOADING, ERROR, DONE }

class RepViewModel : ViewModel() {
    private var _searchName = MutableLiveData<String>()
    val searchName: LiveData<String> = _searchName

    private var _repoName = MutableLiveData<String>()
    val repoName: LiveData<String> = _searchName

    private var _userName = MutableLiveData<String>()
    val userName: LiveData<String> = _userName

    private var _lastCommitDate = MutableLiveData<Date>()
    val lastCommitDate: LiveData<Date> = _lastCommitDate

    fun setSearchName(newRepo:String){
        _searchName.value = newRepo
    }
    init {
        setSearchName("gameBall")
        getGitRepo()
    }
    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<String>()

    // The external immutable LiveData for the request status
    val status: LiveData<String> = _status

    fun getGitRepo() {
        viewModelScope.launch {
            val listResult = GitApi.retrofitService.getRepo(searchName.value.toString())
            _status.value = listResult.body()!!.items[0].owner.login
        }
    }
}