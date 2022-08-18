package com.odogwudev.benshiposts.data.source.remote

import com.odogwudev.benshiposts.data.interactors.RemoteDataSource
import com.odogwudev.benshiposts.data.model.CommentResponse
import com.odogwudev.benshiposts.data.model.PostResponse
import com.odogwudev.benshiposts.data.model.user.UserResponse
import com.odogwudev.benshiposts.data.source.DummyData.dummyCommentsResponse
import com.odogwudev.benshiposts.data.source.DummyData.dummyPost
import com.odogwudev.benshiposts.data.source.DummyData.dummyPostsResponse
import com.odogwudev.benshiposts.data.source.DummyData.dummyUserResponse
import com.odogwudev.benshiposts.data.source.DummyData.dummyUsersResponse
import okio.IOException

class FakeRemoteDataSource : RemoteDataSource {

    var shouldReturnError = false

    override suspend fun getPosts(): Result<List<PostResponse>> = when (shouldReturnError) {
        true -> Result.failure(IOException("Not Found 404"))
        false -> Result.success(dummyPostsResponse)
    }

    override suspend fun getPostById(id: Int): Result<PostResponse> = when (shouldReturnError) {
        true -> Result.failure(IOException("Not Found 404"))
        false -> Result.success(dummyPost)
    }

    override suspend fun getPostByUserId(id: Int): Result<List<PostResponse>> =
        when (shouldReturnError) {
            true -> Result.failure(IOException("Not Found 404"))
            false -> Result.success(dummyPostsResponse)
        }

    override suspend fun getUsers(): Result<List<UserResponse>> =
        when (shouldReturnError) {
            true -> Result.failure(IOException("Not Found 404"))
            false -> Result.success(dummyUsersResponse)
        }


    override suspend fun getUserById(id: Int): Result<UserResponse> =
        when (shouldReturnError) {
            true -> Result.failure(IOException("Not Found 404"))
            false -> Result.success(dummyUserResponse)
        }

    override suspend fun getCommentByPostId(postId: Int): Result<List<CommentResponse>> =
        when (shouldReturnError) {
            true -> Result.failure(IOException("Not Found 404"))
            false -> Result.success(dummyCommentsResponse)
        }

}