package com.example.fragmentsnavigation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fragmentsnavigation.data.RepositoryInfo
import com.example.fragmentsnavigation.network.GitApi
import kotlinx.coroutines.launch

class RepViewModel : ViewModel() {
    private var _searchName = MutableLiveData<String>()
    val searchName: LiveData<String> = _searchName

    private var _repository = MutableLiveData<MutableList<RepositoryInfo>>()
    val repository: LiveData<MutableList<RepositoryInfo>> = _repository

//    private var _userName = MutableLiveData<String>()
//    val userName: LiveData<String> = _userName
//
//    private var _lastCommitDate = MutableLiveData<String>()
//    val lastCommitDate: LiveData<String> = _lastCommitDate

    fun setSearchName(newRepo: String) {
        _searchName.value = newRepo
        getGitRepo()
    }

    init {
        setSearchName("gameBall")
        getGitRepo()
    }

//    // The internal MutableLiveData that stores the status of the most recent request
//    private val _repoName = MutableLiveData<String>()
//
//    // The external immutable LiveData for the request status
//    val repoName: LiveData<String> = _repoName

    fun getGitRepo() {
        viewModelScope.launch {
            val list = mutableListOf<RepositoryInfo>()
            list.clear()
            try {
                val listResult = GitApi.retrofitService.getRepo(searchName.value.toString())

                if (listResult.body()!!.items.size != 0) {
                    for (element in listResult.body()!!.items) {
                        list.add(
                            RepositoryInfo(
                                element.name,
                                element.owner.login,
//                                getLastCommitDate(element.owner.login, element.name)
//                                GitApi.retrofitService.getCommit(
//                                    element.owner.login,
//                                    element.name
//                                ).body()!!.commit.commit.author.date

                            "nodate"
                            )
                        )
                    }
                    _repository.value = list

                } else {
                    list.clear()
                    list.add(
                        RepositoryInfo(
                            "no data",
                            "no data",
                            "no data"
                        )
                    )
                    _repository.value = list
                }

            } catch (e: Exception) {
                list.clear()
                list.add(
                    RepositoryInfo(
                        "no data",
                        "no data",
                        "no data"
                    )
                )
                _repository.value = list
            }
        }
    }

    fun getLastCommitDate(username: String, reponame: String): String {
        var res: String=""
        viewModelScope.launch {
            val listResult = GitApi.retrofitService.getCommit(
                username,
                reponame
            )
            res = listResult.body()!!.commit.commit.author.date
        }
        return res
    }


}