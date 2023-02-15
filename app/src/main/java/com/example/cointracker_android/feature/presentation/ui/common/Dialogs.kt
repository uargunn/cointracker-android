package com.example.cointracker_android.feature.presentation.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.cointracker_android.feature.presentation.ui.theme.Primary
import com.example.cointracker_android.feature.presentation.ui.theme.White

@Composable
fun LoadingDialog() {
    Dialog(
        onDismissRequest = { },
        DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = false)
    ) {
        Row(
            modifier = Modifier
                .background(Primary, CircleShape)
                .padding(
                    horizontal = 20.dp,
                    vertical = 12.dp
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator(
                modifier = Modifier.size(30.dp),
                color = White
            )

            Spacer(modifier = Modifier.size(12.dp))

            Text(
                text = "Please wait",
                style = MaterialTheme.typography.h5,
                color = White
            )
        }
    }
}