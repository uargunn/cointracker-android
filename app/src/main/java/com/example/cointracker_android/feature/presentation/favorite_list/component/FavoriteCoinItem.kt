package com.example.cointracker_android.feature.presentation.favorite_list.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.example.cointracker_android.R
import com.example.cointracker_android.feature.domain.model.Coin
import com.example.cointracker_android.feature.domain.model.CoinDetail
import com.example.cointracker_android.feature.presentation.ui.theme.*

@Composable
fun FavoriteCoinItem(
    coin: CoinDetail?,
    modifier: Modifier = Modifier,
    onItemClick: () -> Unit,
    onDeleteClick: () -> Unit,
) {
    Box(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onItemClick.invoke() }
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

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(OceanBlue)
                    .height(30.dp)
                    .clickable { onDeleteClick.invoke() },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "DELETE",
                    modifier = Modifier.wrapContentSize(),
                    style = TextStyle(
                        color = White,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal
                    )
                )
            }
        }
    }
}