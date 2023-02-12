package com.example.cointracker_android.feature.presentation.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cointracker_android.feature.domain.model.AuthException
import com.example.cointracker_android.feature.domain.use_case.SignInWithEmail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val signInWithEmail: SignInWithEmail
): ViewModel() {

    private val _email = mutableStateOf(FormTextFieldState(
        hint = "Enter your email"
    ))
    val email: State<FormTextFieldState> = _email

    private val _password = mutableStateOf(FormTextFieldState(
        hint = "Enter your password"
    ))
    val password: State<FormTextFieldState> = _password

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.EnteredEmail -> {
                _email.value = email.value.copy(
                    text = event.value
                )
            }
            is LoginEvent.ChangeEmailFocus -> {
                _email.value = email.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            email.value.text.isBlank()
                )
            }
            is LoginEvent.EnteredPassword -> {
                _password.value = password.value.copy(
                    text = event.value
                )
            }
            is LoginEvent.ChangePasswordFocus -> {
                _password.value = password.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            password.value.text.isBlank()
                )
            }
            is LoginEvent.SignIn -> {
                viewModelScope.launch {
                    try {
                        val user = signInWithEmail.invoke(email.value.text, password.value.text)
                        if (user != null) {
                            _eventFlow.emit(UiEvent.SignIn)
                        } else {
                            _eventFlow.emit(UiEvent.ShowSnackbar(
                                "Ops, please check your email/password"
                            ))
                        }
                    } catch (exception: AuthException) {
                        _eventFlow.emit(UiEvent.ShowSnackbar(exception.message.orEmpty()))
                    }
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: String) : UiEvent()
        object SignIn: UiEvent()
    }
}