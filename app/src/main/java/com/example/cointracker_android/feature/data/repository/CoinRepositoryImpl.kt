package com.example.cointracker_android.feature.data.repository

import com.example.cointracker_android.feature.data.local.CoinDao
import com.example.cointracker_android.feature.data.remote.CoinGeckoApi
import com.example.cointracker_android.feature.domain.mapper.CoinMapper
import com.example.cointracker_android.feature.domain.model.Coin
import com.example.cointracker_android.feature.domain.model.CoinDetail
import com.example.cointracker_android.feature.domain.repository.CoinRepository
import com.example.cointracker_android.util.Constants
import com.example.cointracker_android.util.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.HttpException
import java.io.IOException
import kotlin.coroutines.resume

class CoinRepositoryImpl(
    private val api: CoinGeckoApi,
    private val dao: CoinDao,
    private val coinMapper: CoinMapper,
    private val firestore: FirebaseFirestore,
    private val auth: FirebaseAuth
) : CoinRepository {
    override fun getCoins(query: String): Flow<Resource<List<Coin>>> = flow {
        emit(Resource.Loading())

        val coins = dao.getCoins(query)

        try {
            if (coins.isEmpty()) {
                val remoteCoins = api.getCoins()
                dao.insertCoins(remoteCoins.map { coinMapper.map(it) })
            }
        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    message = ":( Something went wrong!",
                    data = coins
                )
            )
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    message = ":( Check your internet connection",
                    data = coins
                )
            )
        }

        emit(Resource.Success(data = dao.getCoins(query)))
    }
    override fun getCoinDetailById(id: String): Flow<Resource<List<CoinDetail>>> = flow {
        emit(Resource.Loading())

        try {
            val coinDetail = api.getCoinDetail(id)
            emit(
                Resource.Success(
                    data = coinDetail.map {
                        coinMapper.map(it)
                    }
                )
            )
        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    message = ":( Something went wrong!",
                    data = null
                )
            )
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    message = ":( Check your internet connection",
                    data = null
                )
            )
        }
    }

    override suspend fun addToFavorites(coin: CoinDetail) : Boolean {
        return suspendCancellableCoroutine { continuation ->
            val userId = auth.currentUser?.uid.orEmpty()
            firestore
                .collection(Constants.Firebase.COLLECTION_USERS)
                .document(userId)
                .collection(Constants.Firebase.COLLECTION_FAVORITES)
                .document(coin.id)
                .set(coin)
                .addOnCompleteListener { task ->
                    continuation.resume(task.isSuccessful)
                }
        }
    }

    override suspend fun getFavoriteCoins() : List<CoinDetail>? {
        return suspendCancellableCoroutine { continuation ->
            val userId = auth.currentUser?.uid.orEmpty()
            firestore
                .collection(Constants.Firebase.COLLECTION_USERS)
                .document(userId)
                .collection(Constants.Firebase.COLLECTION_FAVORITES)
                .addSnapshotListener { value, _ ->
                    if (value != null && value.isEmpty.not()) {
                        val documents = value.documents.mapNotNull {
                            it.toObject(CoinDetail::class.java)
                        }
                        if (continuation.isActive)
                            continuation.resume(documents)
                    } else {
                        continuation.resume(null)
                    }
                }
        }
    }
}