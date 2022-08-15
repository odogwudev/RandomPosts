package com.odogwudev.benshiposts.data.network.repository

import com.odogwudev.benshiposts.data.local.dto.comment.CommentDto
import com.odogwudev.benshiposts.data.local.dto.post.PostDto
import com.odogwudev.benshiposts.data.local.dto.user.UserDto
import com.odogwudev.benshiposts.shared.utils.UIState
import kotlinx.coroutines.flow.Flow


interface AppRepository {
    val posts: Flow<UIState<List<PostDto>>>
    fun getUserById(id: Int): Flow<UIState<UserDto>>
    fun getCommentsByPostId(postId: Int): Flow<UIState<List<CommentDto>>>
}
