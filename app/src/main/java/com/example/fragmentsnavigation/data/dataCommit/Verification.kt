package com.example.fragmentsnavigation.data.dataCommit

data class Verification(
    val payload: Any,
    val reason: String,
    val signature: Any,
    val verified: Boolean
)