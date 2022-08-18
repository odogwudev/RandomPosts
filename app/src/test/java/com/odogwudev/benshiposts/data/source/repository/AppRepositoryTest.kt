package com.odogwudev.benshiposts.data.source.repository

import com.odogwudev.benshiposts.MainCoroutineRule
import com.odogwudev.benshiposts.data.interactors.RemoteDataSource
import com.odogwudev.benshiposts.data.local.dto.post.PostDto
import com.odogwudev.benshiposts.data.network.repository.AppRepository
import com.odogwudev.benshiposts.data.network.repository.AppRepositoryImpl
import com.odogwudev.benshiposts.data.source.DummyData.dummyCommentsDto
import com.odogwudev.benshiposts.data.source.DummyData.dummyPostDto
import com.odogwudev.benshiposts.data.source.DummyData.dummyUserDto
import com.odogwudev.benshiposts.data.source.DummyData.postId
import com.odogwudev.benshiposts.data.source.DummyData.userId
import com.odogwudev.benshiposts.data.source.remote.FakeRemoteDataSource
import com.odogwudev.benshiposts.shared.utils.UIState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class AppRepositoryTest {

    @Mock
    private lateinit var remoteDataSource: RemoteDataSource

    private lateinit var appRepository: AppRepository

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setUp() {
        remoteDataSource = FakeRemoteDataSource()
        appRepository = AppRepositoryImpl(remoteDataSource, mainCoroutineRule.testDispatcher)
    }

    @Test
    fun getPosts() = runTest {
        val actual = appRepository.posts.toList()

        assertEquals(UIState.Loading<List<PostDto>>(), actual.first())
        assertEquals(UIState.Success(dummyPostDto), actual[1])
    }

    @Test
    fun getUserById() = runTest {
        val actual = appRepository.getUserById(userId).toList()

        assertEquals(UIState.Loading<List<PostDto>>(), actual.first())
        assertEquals(UIState.Success(dummyUserDto), actual[1])
    }

    @Test
    fun getCommentByPostId() = runTest {
        val actual = appRepository.getCommentsByPostId(postId).toList()

        assertEquals(UIState.Loading<List<PostDto>>(), actual.first())
        assertEquals(UIState.Success(dummyCommentsDto), actual[1])
    }
}