package com.rapptrlabs.androidtest.ui.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rapptrlabs.androidtest.data.model.ChatMessageModel
import com.rapptrlabs.androidtest.data.model.ChatsResponse
import com.rapptrlabs.androidtest.data.model.UiState
import com.rapptrlabs.androidtest.data.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val appRepository: AppRepository
) : ViewModel() {

    private val _thread: MutableStateFlow<List<ChatMessageModel>> = MutableStateFlow(emptyList())
    val thread: StateFlow<List<ChatMessageModel>>
        get() = _thread

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.Empty)
    val uiState: StateFlow<UiState>
        get() = _uiState

    init {
        getChatThread()
    }

    fun resetStates() {
        _uiState.value = UiState.Initial
    }

    fun getChatThread() {
        _uiState.value = UiState.Loading()

        viewModelScope.launch {

            val data = appRepository.getChats()
            Timber.i("chats response ${data.data}")

            _uiState.value = UiState.Success("")

            _thread.value = data.data
        }
    }
}