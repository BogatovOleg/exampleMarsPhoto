package com.example.fragmentsnavigation.data

data class DataRepoSearch(
    val incomplete_results: Boolean,
    val items: List<Item>,
    val total_count: Int
)