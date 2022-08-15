package com.odogwudev.benshiposts.data.interactors.impl

import android.util.Log
import com.odogwudev.benshiposts.data.interactors.RemoteDataSource
import com.odogwudev.benshiposts.data.model.CommentResponse
import com.odogwudev.benshiposts.data.model.PostResponse
import com.odogwudev.benshiposts.data.model.user.UserResponse
import com.odogwudev.benshiposts.data.network.ApiService
import okio.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiService
) : RemoteDataSource {

    private val TAG: String = this.javaClass.simpleName

    override suspend fun getPosts(): Result<List<PostResponse>> {
        return try {
            val response = apiService.getPosts()
            return if (response.isSuccessful) {
                Result.success(response.body()!!)
            } else {
                Result.failure(IOException(response.message()))
            }
        } catch (t: Throwable) {
            Log.d("$TAG getPosts", "$t")
            Result.failure(t)
        }
    }

    override suspend fun getPostById(id: Int): Result<PostResponse> {
        return try {
            val response = apiService.getPostById(id)
            return if (response.isSuccessful) {
                Result.success(response.body()!!)
            } else {
                Result.failure(IOException(response.message()))
            }
        } catch (t: Throwable) {
            Log.d("$TAG getPostByUserId", "$t")
            Result.failure(t)
        }
    }

    override suspend fun getPostByUserId(id: Int): Result<List<PostResponse>> = try {
        val response = apiService.getPostByUserId(id)
        if (response.isSuccessful) {
            Result.success(response.body()!!)
        } else {
            Result.failure(IOException(response.message()))
        }
    } catch (t: Throwable) {
        Log.d("$TAG getPostByUserId", "$t")
        Result.failure(t)
    }


    override suspend fun getUsers(): Result<List<UserResponse>> = try {
        val response = apiService.getUsers()
        if (response.isSuccessful) {
            Result.success(response.body()!!)
        } else {
            Result.failure(IOException(response.message()))
        }
    } catch (t: Throwable) {
        Log.d("$TAG getPostByUserId", "$t")
        Result.failure(t)
    }

    override suspend fun getUserById(id: Int): Result<UserResponse> = try {
        val response = apiService.getUserById(id)
        if (response.isSuccessful) {
            Result.success(response.body()!!)
        } else {
            Result.failure(IOException(response.message()))
        }
    } catch (t: Throwable) {
        Log.d("$TAG getPostByUserId", "$t")
        Result.failure(t)
    }

    override suspend fun getCommentByPostId(postId: Int): Result<List<CommentResponse>> = try {
        val response = apiService.getCommentByPostId(postId)
        if (response.isSuccessful) {
            Result.success(response.body()!!)
        } else {
            Result.failure(IOException(response.message()))
        }
    } catch (t: Throwable) {
        Log.d("$TAG getPostByUserId", "$t")
        Result.failure(t)
    }

}
