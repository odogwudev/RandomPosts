package com.odogwudev.benshiposts.ui.posts.detail

import androidx.lifecycle.*
import com.odogwudev.benshiposts.data.local.dto.comment.CommentDto
import com.odogwudev.benshiposts.data.network.repository.AppRepository
import com.odogwudev.benshiposts.shared.utils.MainDispatcher
import com.odogwudev.benshiposts.shared.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

@HiltViewModel
class PostDetailViewModel @Inject constructor(
    private val appRepository: AppRepository,
    @MainDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _comment = MutableLiveData<Int>()
    val comment: LiveData<UIState<List<CommentDto>>> = _comment.switchMap {
        appRepository.getCommentsByPostId(it).asLiveData(dispatcher)
    }

    fun getCommentByPostId(postId: Int) {
        _comment.value = postId
    }

}
