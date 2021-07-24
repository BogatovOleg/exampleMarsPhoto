package com.example.fragmentsnavigation.network

import com.example.fragmentsnavigation.data.dataCommit.DataCommit
import com.example.fragmentsnavigation.data.dataRepo.DataRepoSearch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import retrofit2.http.GET
import retrofit2.http.Path

import retrofit2.http.Query

private const val BASE_URL =
    "https://api.github.com"


private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface GitApiService {
    @GET("/search/repositories")
    suspend fun getRepo(@Query("q") name: String): Response<DataRepoSearch>

    @GET("/repos/{owner}/{repo}/branches/master")
    suspend fun getCommit(
        @Path("owner") owner: String,
        @Path("repo") repo: String,
    ): Response<DataCommit>
}

object GitApi {
    val retrofitService: GitApiService by lazy { retrofit.create(GitApiService::class.java) }
}
