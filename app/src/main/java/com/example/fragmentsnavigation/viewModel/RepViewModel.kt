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
    private var _repositoryName = MutableLiveData<String>()
    val repositoryName: LiveData<String> = _repositoryName

    private var _lastCommitDate = MutableLiveData<Date>()
    val lastCommitDate: LiveData<Date> = _lastCommitDate

    private var _userName = MutableLiveData<String>()
    val userName: LiveData<String> = _userName

    fun setRepositoryName(newRepo:String){
        _repositoryName.value = newRepo
    }

    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<String>()

    // The external immutable LiveData for the request status
    val status: LiveData<String> = _status

    init {
        getMarsPhotos()
    }

    fun getMarsPhotos() {

        viewModelScope.launch {
            val listResult = GitApi.retrofitService.getRepo("GitHubSearch")
            _status.value = listResult
        }
    }
}