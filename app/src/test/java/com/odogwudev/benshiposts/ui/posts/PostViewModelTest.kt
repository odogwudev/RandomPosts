package com.odogwudev.benshiposts.ui.posts

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.odogwudev.benshiposts.MainCoroutineRule
import com.odogwudev.benshiposts.data.network.repository.AppRepository
import com.odogwudev.benshiposts.data.source.DummyData.dummyPostDto
import com.odogwudev.benshiposts.data.source.repository.FakeAppRepository
import com.odogwudev.benshiposts.getOrAwaitValue
import com.odogwudev.benshiposts.shared.utils.UIState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class PostViewModelTest {

    private lateinit var appRepository: AppRepository

    private lateinit var postViewModel: PostViewModel

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule(UnconfinedTestDispatcher())

    @Before
    fun setUp() {
        appRepository = FakeAppRepository(mainCoroutineRule.testDispatcher)
        postViewModel = PostViewModel(appRepository, mainCoroutineRule.testDispatcher)
    }

    @Test
    fun getPosts() {
        val actual = postViewModel.posts

        assertTrue(actual.getOrAwaitValue() is UIState.Loading)
        assertEquals(UIState.Success(dummyPostDto), actual.getOrAwaitValue())
    }
}