package com.odogwudev.benshiposts.data.source.repository

import com.odogwudev.benshiposts.data.local.dto.post.PostDto
import com.odogwudev.benshiposts.data.local.dto.comment.CommentDto
import com.odogwudev.benshiposts.data.local.dto.user.UserDto
import com.odogwudev.benshiposts.data.network.repository.AppRepository
import com.odogwudev.benshiposts.data.source.DummyData.dummyCommentsDto
import com.odogwudev.benshiposts.data.source.DummyData.dummyPostDto
import com.odogwudev.benshiposts.data.source.DummyData.dummyUserDto
import com.odogwudev.benshiposts.shared.utils.UIState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.test.TestDispatcher

@ExperimentalCoroutinesApi
class FakeAppRepository(
    private val testDispatcher: TestDispatcher
) : AppRepository {

    override val posts = flowOf<UIState<List<PostDto>>>(
        UIState.Success(dummyPostDto)
    ).onStart {
        emit(UIState.Loading())
    }.flowOn(testDispatcher)

    override fun getUserById(id: Int) = flowOf<UIState<UserDto>>(
        UIState.Success(dummyUserDto)
    ).onStart {
        emit(UIState.Loading())
    }.flowOn(testDispatcher)

    override fun getCommentsByPostId(postId: Int) = flowOf<UIState<List<CommentDto>>>(
        UIState.Success(dummyCommentsDto)
    ).onStart {
        emit(UIState.Loading())
    }.flowOn(testDispatcher)

}