package com.example.fragmentsnavigation.network

import retrofit2.Retrofit

import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

import retrofit2.http.Query

private const val BASE_URL =
    "https://api.github.com"


private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface GitApiService {
    @GET("/search/repositories")
    suspend fun getRepo(@Query("q") name: String): String

    @GET("/repos/{owner}/{repo}/branches/master")
    suspend fun getCommit(
        @Path("owner") owner: String,
        @Path("repo") repo: String,
    ): String
}

object GitApi {
    val retrofitService: GitApiService by lazy { retrofit.create(GitApiService::class.java) }
}
