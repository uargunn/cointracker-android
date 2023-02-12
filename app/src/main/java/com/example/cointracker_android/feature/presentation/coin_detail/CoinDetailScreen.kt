package com.example.cointracker_android.feature.presentation.coin_detail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import com.example.cointracker_android.R
import com.example.cointracker_android.feature.presentation.ui.common.PrimaryButton
import com.example.cointracker_android.feature.presentation.ui.theme.Primary
import com.example.cointracker_android.feature.presentation.ui.theme.White

@Composable
fun CoinDetailScreen(
    navController: NavController,
    viewModel: CoinDetailViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    Scaffold {
        Box(modifier = Modifier.fillMaxSize()) {
            state.coinDetail?.let { coinInfo ->
                Column(modifier = Modifier.fillMaxWidth()) {
                    Box {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .offset(y = 80.dp)
                                .padding(horizontal = 16.dp)
                                .align(Alignment.TopCenter),
                            shape = RoundedCornerShape(8.dp),
                            elevation = 4.dp,
                            backgroundColor = White,
                            border = BorderStroke(2.dp, Primary)
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 60.dp, bottom = 20.dp)
                                    .padding(horizontal = 16.dp),
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Row(modifier = Modifier.fillMaxWidth()) {
                                    Text(
                                        text = stringResource(id = R.string.coin_name),
                                        style = MaterialTheme.typography.body1,
                                        textAlign = TextAlign.Start,
                                        modifier = Modifier.weight(1f),
                                        maxLines = 2,
                                        overflow = TextOverflow.Ellipsis,
                                        color = Color.DarkGray
                                    )
                                    Text(
                                        text = coinInfo.name,
                                        style = MaterialTheme.typography.body1,
                                        textAlign = TextAlign.End,
                                        modifier = Modifier.weight(1f),
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis,
                                        color = Color.Black
                                    )
                                }

                                Row(modifier = Modifier.fillMaxWidth()) {
                                    Text(
                                        text = stringResource(id = R.string.coin_current_price),
                                        style = MaterialTheme.typography.body1,
                                        textAlign = TextAlign.Start,
                                        modifier = Modifier.weight(1f),
                                        maxLines = 2,
                                        overflow = TextOverflow.Ellipsis,
                                        color = Color.DarkGray
                                    )
                                    Text(
                                        text = coinInfo.currentPrice.toString(),
                                        style = MaterialTheme.typography.body1,
                                        textAlign = TextAlign.End,
                                        modifier = Modifier.weight(1f),
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis,
                                        color = Color.Black
                                    )
                                }

                                Row(modifier = Modifier.fillMaxWidth()) {
                                    Text(
                                        text = stringResource(id = R.string.coin_change_percentage),
                                        style = MaterialTheme.typography.body1,
                                        textAlign = TextAlign.Start,
                                        modifier = Modifier.weight(1f),
                                        maxLines = 2,
                                        overflow = TextOverflow.Ellipsis,
                                        color = Color.DarkGray
                                    )
                                    Text(
                                        text = coinInfo.priceChangePercentage24h.toString(),
                                        style = MaterialTheme.typography.body1,
                                        textAlign = TextAlign.End,
                                        modifier = Modifier.weight(1f),
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis,
                                        color = Color.Black
                                    )
                                }
                            }
                        }

                        Card(
                            modifier = Modifier
                                .wrapContentSize()
                                .align(Alignment.TopCenter)
                                .offset(y = 40.dp),
                            elevation = 4.dp,
                            shape = CircleShape,
                            backgroundColor = White,
                            border = BorderStroke(2.dp, Primary)
                        ) {
                            SubcomposeAsyncImage(
                                model = state.coinDetail.image,
                                contentDescription = state.coinDetail.name,
                                loading = {
                                    CircularProgressIndicator()
                                },
                                modifier = Modifier
                                    .size(80.dp)
                                    .align(Alignment.Center),
                            )
                        }
                    }

                    PrimaryButton(
                        text = "Set Your Time",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .height(54.dp)
                            .offset(y = 80.dp),
                        roundedCornerShape = 8,
                        onClick = {

                        }
                    )
                }
            }

            // region Loading Helper
            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            // endregion
        }
    }
}