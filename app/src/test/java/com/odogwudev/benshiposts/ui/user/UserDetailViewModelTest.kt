package com.odogwudev.benshiposts.ui.user

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.odogwudev.benshiposts.MainCoroutineRule
import com.odogwudev.benshiposts.data.network.repository.AppRepository
import com.odogwudev.benshiposts.data.source.DummyData.dummyUserDto
import com.odogwudev.benshiposts.data.source.DummyData.userId
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
class UserDetailViewModelTest {

    private lateinit var appRepository: AppRepository

    private lateinit var userDetailViewModel: UserDetailViewModel

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule(UnconfinedTestDispatcher())

    @Before
    fun setUp() {
        appRepository = FakeAppRepository(mainCoroutineRule.testDispatcher)
        userDetailViewModel = UserDetailViewModel(appRepository, mainCoroutineRule.testDispatcher)
    }


    @Test
    fun getUser() = runTest {
        userDetailViewModel.getUserDetail(userId)
        val actual = userDetailViewModel.user

        assertTrue(actual.getOrAwaitValue() is UIState.Loading)
        assertEquals(UIState.Success(dummyUserDto), actual.getOrAwaitValue())
    }
}