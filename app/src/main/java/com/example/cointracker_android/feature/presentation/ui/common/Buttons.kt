package com.example.cointracker_android.feature.presentation.ui.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.cointracker_android.feature.presentation.ui.theme.Primary

@Composable
fun PrimaryButton(
    text: String,
    modifier: Modifier = Modifier,
    roundedCornerShape: Int = 50,
    backgroundColor: Color = Primary,
    textColor: Color = Color.White,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        shape = RoundedCornerShape(roundedCornerShape),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor
        ),
        onClick = {
            onClick.invoke()
        },
        elevation = ButtonDefaults.elevation(4.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.button,
            modifier = Modifier.padding(6.dp),
            color = textColor
        )
    }
}

@Composable
fun PrimaryOutlinedButton(
    text: String,
    modifier: Modifier = Modifier,
    roundedCornerShape: Int = 50,
    textColor: Color = Primary,
    onClick: () -> Unit
) {
    OutlinedButton(
        modifier = modifier,
        shape = RoundedCornerShape(roundedCornerShape),
        border = BorderStroke(2.dp, Primary),
        onClick = {
            onClick.invoke()
        },
        elevation = ButtonDefaults.elevation(4.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.button,
            modifier = Modifier.padding(6.dp),
            color = textColor
        )
    }
}