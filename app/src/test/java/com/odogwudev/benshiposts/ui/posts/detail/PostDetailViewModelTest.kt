package com.odogwudev.benshiposts.ui.posts.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.odogwudev.benshiposts.MainCoroutineRule
import com.odogwudev.benshiposts.data.network.repository.AppRepository
import com.odogwudev.benshiposts.data.source.DummyData.dummyCommentsDto
import com.odogwudev.benshiposts.data.source.DummyData.postId
import com.odogwudev.benshiposts.data.source.repository.FakeAppRepository
import com.odogwudev.benshiposts.getOrAwaitValue
import com.odogwudev.benshiposts.shared.utils.UIState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class PostDetailViewModelTest {

    private lateinit var appRepository: AppRepository

    private lateinit var postDetailViewModel: PostDetailViewModel

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule(UnconfinedTestDispatcher())

    @Before
    fun setUp() {
        appRepository = FakeAppRepository(mainCoroutineRule.testDispatcher)
        postDetailViewModel = PostDetailViewModel(appRepository, mainCoroutineRule.testDispatcher)
    }

    @Test
    fun getComment() = runTest {
        postDetailViewModel.getCommentByPostId(postId)
        val actual = postDetailViewModel.comment

        assertTrue(actual.getOrAwaitValue() is UIState.Loading)
        assertEquals(UIState.Success(dummyCommentsDto), actual.getOrAwaitValue())
    }

}