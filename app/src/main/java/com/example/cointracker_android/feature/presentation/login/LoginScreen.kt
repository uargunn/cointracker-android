package com.example.cointracker_android.feature.presentation.login

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.cointracker_android.feature.presentation.ui.common.FormTextField
import com.example.cointracker_android.feature.presentation.ui.common.PrimaryButton
import com.example.cointracker_android.feature.presentation.util.Screen
import kotlinx.coroutines.flow.collectLatest

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val emailState = viewModel.email.value
    val passwordState = viewModel.password.value

    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest {  event ->
            when (event) {
                is LoginViewModel.UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
                is LoginViewModel.UiEvent.SignIn -> {
                    navController.navigate(Screen.CoinListScreen.route)
                }
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            FormTextField(
                text = emailState.text,
                hint = emailState.hint,
                onValueChange = {
                    viewModel.onEvent(LoginEvent.EnteredEmail(it))
                },
                onFocusChange = {
                    viewModel.onEvent(LoginEvent.ChangeEmailFocus(it))
                },
                isHintVisible = emailState.isHintVisible,
                textStyle = MaterialTheme.typography.body1
            )

            Spacer(modifier = Modifier.height(16.dp))

            FormTextField(
                text = passwordState.text,
                hint = passwordState.hint,
                onValueChange = {
                    viewModel.onEvent(LoginEvent.EnteredPassword(it))
                },
                onFocusChange = {
                    viewModel.onEvent(LoginEvent.ChangePasswordFocus(it))
                },
                isHintVisible = passwordState.isHintVisible,
                textStyle = MaterialTheme.typography.body1
            )

            Spacer(modifier = Modifier.height(16.dp))

            PrimaryButton(
                text = "Sign In",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(54.dp),
                roundedCornerShape = 8,
                onClick = {
                    viewModel.onEvent(LoginEvent.SignIn)
                }
            )
        }
    }
}