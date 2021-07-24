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


    private var _userName = MutableLiveData<String>()
    val userName: LiveData<String> = _userName

    private var _lastCommitDate = MutableLiveData<String>()
    val lastCommitDate: LiveData<String> = _lastCommitDate

    fun setSearchName(newRepo: String) {
        _searchName.value = newRepo
    }

    init {
        setSearchName("gameBall")
        getGitRepo()
    }

    // The internal MutableLiveData that stores the status of the most recent request
    private val _repoName = MutableLiveData<String>()

    // The external immutable LiveData for the request status
    val repoName: LiveData<String> = _repoName

    fun getGitRepo() {
        viewModelScope.launch {
            try {
                val listResult = GitApi.retrofitService.getRepo(searchName.value.toString())
                _repoName.value = listResult.body()!!.items[0].name
                _userName.value = listResult.body()!!.items[0].owner.login
                getLastCommitDate()
            } catch (e: Exception) {
                _repoName.value = ""
            }

        }
    }

    fun getLastCommitDate() {
        viewModelScope.launch {
            try {
                val listResult = GitApi.retrofitService.getCommit(userName.value.toString(), repoName.value.toString())
                _lastCommitDate.value = listResult.body()!!.commit.commit.author.date
            } catch (e: Exception) {
                _lastCommitDate.value = ""
            }
        }
    }
}