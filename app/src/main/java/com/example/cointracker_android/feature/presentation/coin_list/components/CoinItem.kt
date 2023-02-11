package com.example.cointracker_android.feature.presentation.coin_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.cointracker_android.feature.domain.model.Coin

@Composable
fun CoinItem(
    coin: Coin,
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 10.dp,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .clickable { onClick() }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(end = 32.dp)
        ) {
            Text(
                text = coin.name,
                style = MaterialTheme.typography.h6,
                maxLines = 1
            )
        }
    }
}