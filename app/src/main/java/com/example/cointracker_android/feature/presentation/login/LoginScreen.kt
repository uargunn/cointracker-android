package com.example.cointracker_android.feature.presentation.login

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.cointracker_android.R
import com.example.cointracker_android.feature.presentation.ui.common.FormTextField
import com.example.cointracker_android.feature.presentation.ui.common.PrimaryButton
import com.example.cointracker_android.feature.presentation.ui.theme.Primary
import kotlinx.coroutines.flow.collectLatest

@Composable
fun LoginScreen(
    onNavToHomePage: () -> Unit,
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
                    onNavToHomePage.invoke()
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
            Text(
                text = stringResource(id = R.string.sign_up),
                style = MaterialTheme.typography.h5,
                color = Primary,
                fontWeight = FontWeight.Medium,
                lineHeight = 20.sp
            )

            Spacer(modifier = Modifier.height(20.dp))

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
                textStyle = MaterialTheme.typography.body1,
                singleLine = true
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
                textStyle = MaterialTheme.typography.body1,
                singleLine = true,
                isPassword = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            PrimaryButton(
                text = stringResource(id = R.string.action_sign_up),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                roundedCornerShape = 8,
                onClick = {
                    viewModel.onEvent(LoginEvent.SignIn)
                }
            )
        }
    }

    LaunchedEffect(key1 = viewModel.hasUser) {
        if (viewModel.hasUser) {
            onNavToHomePage.invoke()
        }
    }
}