package com.example.fragmentsnavigation.data.dataCommit

data class CommitX(
    val author: Author,
    val comment_count: Int,
    val committer: Committer,
    val message: String,
    val tree: Tree,
    val url: String,
    val verification: Verification
)