package com.example.fragmentsnavigation.data.dataCommit

data class Commit(
    val author: Any,
    val comments_url: String,
    val commit: CommitX,
    val committer: Any,
    val html_url: String,
    val node_id: String,
    val parents: List<Parent>,
    val sha: String,
    val url: String
)