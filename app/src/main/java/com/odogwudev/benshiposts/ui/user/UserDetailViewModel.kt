package com.odogwudev.benshiposts.ui.user

import androidx.lifecycle.*
import com.odogwudev.benshiposts.data.local.dto.user.UserDto
import com.odogwudev.benshiposts.data.network.repository.AppRepository
import com.odogwudev.benshiposts.shared.utils.MainDispatcher
import com.odogwudev.benshiposts.shared.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(
    private val appRepository: AppRepository,
    @MainDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {


    private val _userId: MutableLiveData<Int> = MutableLiveData<Int>()
    val user: LiveData<UIState<UserDto>> = _userId.switchMap {
        appRepository.getUserById(it).asLiveData(dispatcher)
    }

    fun getUserDetail(userId: Int) {
        _userId.value = userId
    }

}
