package com.odogwudev.benshiposts.data.source.remote

import com.odogwudev.benshiposts.data.interactors.RemoteDataSource
import com.odogwudev.benshiposts.data.interactors.impl.RemoteDataSourceImpl
import com.odogwudev.benshiposts.data.network.ApiService
import com.odogwudev.benshiposts.data.source.DummyData.dummyCommentsResponse
import com.odogwudev.benshiposts.data.source.DummyData.dummyPost
import com.odogwudev.benshiposts.data.source.DummyData.dummyPostsResponse
import com.odogwudev.benshiposts.data.source.DummyData.dummyUserResponse
import com.odogwudev.benshiposts.data.source.DummyData.dummyUsersResponse
import com.odogwudev.benshiposts.data.source.DummyData.id
import com.odogwudev.benshiposts.data.source.DummyData.postId
import com.odogwudev.benshiposts.data.source.DummyData.userId
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class RemoteDataSourceTest {

    @Mock
    private lateinit var apiService: ApiService

    private lateinit var remoteDataSource: RemoteDataSource

    @Before
    fun setUp() {
        remoteDataSource = RemoteDataSourceImpl(apiService)
    }

    @Test
    fun getPosts() = runTest {
        val expected = Response.success(dummyPostsResponse)
        `when`(apiService.getPosts()).thenReturn(expected)

        val actual = remoteDataSource.getPosts()

        assertEquals(expected.body(), actual.getOrThrow())
    }

    @Test
    fun getPostById() = runTest {
        val expected = Response.success(dummyPost)
        `when`(apiService.getPostById(id)).thenReturn(expected)

        val actual = remoteDataSource.getPostById(id)

        assertEquals(expected.body(), actual.getOrThrow())
    }

    @Test
    fun getPostByUserId() = runTest {
        val expected = Response.success(dummyPostsResponse)
        `when`(apiService.getPostByUserId(userId)).thenReturn(expected)

        val actual = remoteDataSource.getPostByUserId(userId)

        assertEquals(expected.body(), actual.getOrThrow())
    }

    @Test
    fun getUsers() = runTest {
        val expected = Response.success(dummyUsersResponse)
        `when`(apiService.getUsers()).thenReturn(expected)

        val actual = remoteDataSource.getUsers()

        assertEquals(expected.body(), actual.getOrThrow())
    }

    @Test
    fun getUserById() = runTest {
        val expected = Response.success(dummyUserResponse)
        `when`(apiService.getUserById(userId)).thenReturn(expected)

        val actual = remoteDataSource.getUserById(userId)

        assertEquals(expected.body(), actual.getOrThrow())
    }

    @Test
    fun getCommentByPostId() = runTest {
        val expected = Response.success(dummyCommentsResponse)
        `when`(apiService.getCommentByPostId(postId)).thenReturn(expected)

        val actual = remoteDataSource.getCommentByPostId(postId)

        assertEquals(expected.body(), actual.getOrThrow())
    }


}