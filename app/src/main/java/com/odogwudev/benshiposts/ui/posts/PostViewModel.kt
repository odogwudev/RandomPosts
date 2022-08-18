package com.odogwudev.benshiposts.ui.posts

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.odogwudev.benshiposts.data.local.dto.post.PostDto
import com.odogwudev.benshiposts.data.network.repository.AppRepository
import com.odogwudev.benshiposts.shared.utils.MainDispatcher
import com.odogwudev.benshiposts.shared.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val appDataSource: AppRepository,
    @MainDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    val posts: LiveData<UIState<List<PostDto>>> = appDataSource.posts.asLiveData(dispatcher)

}
