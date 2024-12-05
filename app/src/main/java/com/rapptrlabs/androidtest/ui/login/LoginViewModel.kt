package com.rapptrlabs.androidtest.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rapptrlabs.androidtest.data.model.UiState
import com.rapptrlabs.androidtest.data.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: AppRepository
): ViewModel(){

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.Empty)
    val uiState: StateFlow<UiState>
        get() = _uiState

    private val _email = MutableStateFlow<String>("")
    val email: StateFlow<String> = _email

    private val _password = MutableStateFlow<String>("")
    val password: StateFlow<String> = _password

    fun setEmail(email: String) {
        _email.value = email
    }

    fun setPassword(password: String) {
        _password.value = password
    }

    var code = ""
    var message = ""
    var duration = ""

    fun resetStates() {
        _uiState.value = UiState.Initial
    }

    fun loginUser(email: String, password: String) {
        _uiState.value = UiState.Loading()
        viewModelScope.launch {
            val response = repository.login(email, password)

                code = response.first
                message = response.second
                duration = response.third

                Timber.i("returned response ${response.first} with message ${response.second} ")

                _uiState.value = UiState.Success("")

        }
    }
}