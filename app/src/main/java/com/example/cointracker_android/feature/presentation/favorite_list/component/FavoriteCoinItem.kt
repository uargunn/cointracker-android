package com.example.cointracker_android.feature.presentation.favorite_list.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.example.cointracker_android.feature.domain.model.Coin
import com.example.cointracker_android.feature.domain.model.CoinDetail
import com.example.cointracker_android.feature.presentation.ui.theme.Primary
import com.example.cointracker_android.feature.presentation.ui.theme.White

@Composable
fun FavoriteCoinItem(
    coin: CoinDetail?,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Card(
                modifier = Modifier.wrapContentSize(),
                elevation = 4.dp,
                shape = CircleShape,
                backgroundColor = White,
                border = BorderStroke(2.dp, Primary)
            ) {
                SubcomposeAsyncImage(
                    model = coin?.image.orEmpty(),
                    contentDescription = coin?.name,
                    loading = {
                        CircularProgressIndicator()
                    },
                    modifier = Modifier.size(60.dp)
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            Column {
                Text(
                    text = coin?.name.orEmpty(),
                    style = MaterialTheme.typography.body1,
                    maxLines = 1
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = coin?.symbol.orEmpty(),
                    style = MaterialTheme.typography.body2,
                    maxLines = 1
                )
            }
        }
    }
}