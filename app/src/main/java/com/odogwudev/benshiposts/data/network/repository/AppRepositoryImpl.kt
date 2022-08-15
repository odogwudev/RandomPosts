package com.odogwudev.benshiposts.data.network.repository

import com.odogwudev.benshiposts.data.interactors.RemoteDataSource
import com.odogwudev.benshiposts.data.local.dto.comment.CommentDto
import com.odogwudev.benshiposts.data.local.dto.post.PostDto
import com.odogwudev.benshiposts.data.local.dto.user.UserDto
import com.odogwudev.benshiposts.data.model.PostResponse
import com.odogwudev.benshiposts.data.model.toDto
import com.odogwudev.benshiposts.data.model.user.toDto
import com.odogwudev.benshiposts.shared.utils.IoDispatcher
import com.odogwudev.benshiposts.shared.utils.UIState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : AppRepository {

    override val posts = flow<UIState<List<PostDto>>> {
        val userResponses = remoteDataSource.getUsers().getOrThrow()
        val posts = mutableListOf<PostDto>()
        userResponses.forEach { userResponse ->
            val postDto = getPostsByUserId(userResponse.id ?: 0)
                .map { postResponse ->
                    PostDto(
                        id = postResponse.id ?: 0,
                        body = postResponse.body.orEmpty(),
                        title = postResponse.title.orEmpty(),
                        userCompanyName = userResponse.company?.name.orEmpty(),
                        username = userResponse.username.orEmpty(),
                        userId = userResponse.id ?: 0
                    )
                }
            posts.addAll(postDto)
        }
        emit(UIState.Success(posts))
    }.onStart {
        emit(UIState.Loading())
    }.catch {
        emit(UIState.Error(it.message))
    }.flowOn(dispatcher)

    private suspend fun getPostsByUserId(userId: Int): List<PostResponse> {
        return remoteDataSource.getPostByUserId(userId).getOrThrow()
    }

    override fun getUserById(id: Int) = flow<UIState<UserDto>> {
        val user = remoteDataSource.getUserById(id)
            .getOrThrow()
            .toDto()

        emit(UIState.Success(user))
    }.onStart {
        emit(UIState.Loading())
    }.catch {
        emit(UIState.Error())
    }.flowOn(dispatcher)

    override fun getCommentsByPostId(postId: Int) = flow<UIState<List<CommentDto>>> {
        val comments = remoteDataSource.getCommentByPostId(postId)
            .getOrThrow()
            .map { it.toDto() }

        emit(UIState.Success(comments))
    }.onStart {
        emit(UIState.Loading())
    }.catch {
        emit(UIState.Error())
    }.flowOn(dispatcher)
}
