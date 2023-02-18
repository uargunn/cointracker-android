package com.example.cointracker_android.feature.presentation.coin_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cointracker_android.feature.domain.model.Coin
import com.example.cointracker_android.feature.presentation.ui.theme.Dark
import com.example.cointracker_android.feature.presentation.ui.theme.Gray

@Composable
fun CoinItem(
    coin: Coin,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = coin.name,
                style = MaterialTheme.typography.body1,
                maxLines = 1,
                color = Dark
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = coin.symbol,
                style = MaterialTheme.typography.body2,
                maxLines = 1,
                color = Gray
            )
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .size(30.dp)
                .background(MaterialTheme.colors.secondary.copy(alpha = 0.1f), CircleShape)
                .align(Alignment.CenterEnd)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "Go",
                modifier = Modifier.size(15.dp),
                tint = MaterialTheme.colors.secondary
            )
        }
    }
}