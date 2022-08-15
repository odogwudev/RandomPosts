package com.odogwudev.benshiposts.data.interactors

import com.odogwudev.benshiposts.data.model.CommentResponse
import com.odogwudev.benshiposts.data.model.PostResponse
import com.odogwudev.benshiposts.data.model.user.UserResponse

interface RemoteDataSource {

    suspend fun getPosts(): Result<List<PostResponse>>

    suspend fun getPostById(id: Int): Result<PostResponse>

    suspend fun getPostByUserId(id: Int): Result<List<PostResponse>>

    suspend fun getUsers(): Result<List<UserResponse>>

    suspend fun getUserById(id: Int): Result<UserResponse>

    suspend fun getCommentByPostId(postId: Int): Result<List<CommentResponse>>
}
