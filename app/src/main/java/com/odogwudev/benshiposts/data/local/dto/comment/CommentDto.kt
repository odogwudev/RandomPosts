package com.odogwudev.benshiposts.data.local.dto.comment

data class CommentDto(
    val id: Int,
    val name: String,
    val postId: Int,
    val body: String,
    val email: String
)
