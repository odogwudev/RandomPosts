package com.odogwudev.benshiposts.data.network

import com.odogwudev.benshiposts.data.model.CommentResponse
import com.odogwudev.benshiposts.data.model.PostResponse
import com.odogwudev.benshiposts.data.model.user.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET(value = "posts")
    suspend fun getPosts(): Response<List<PostResponse>>

    @GET(value = "posts/{id}")
    suspend fun getPostById(@Path(value = "id") id: Int): Response<PostResponse>

    @GET(value = "posts")
    suspend fun getPostByUserId(@Query(value = "userId") userId: Int): Response<List<PostResponse>>

    @GET(value = "users")
    suspend fun getUsers(): Response<List<UserResponse>>

    @GET(value = "users/{id}")
    suspend fun getUserById(@Path(value = "id") id: Int): Response<UserResponse>

    @GET(value = "comments")
    suspend fun getCommentByPostId(@Query(value = "postId") postId: Int): Response<List<CommentResponse>>
}
