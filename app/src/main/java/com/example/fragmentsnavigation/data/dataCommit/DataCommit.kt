package com.example.fragmentsnavigation.data.dataCommit

data class DataCommit(
    val _links: Links,
    val commit: Commit,
    val name: String,
    val `protected`: Boolean,
    val protection: Protection,
    val protection_url: String
)