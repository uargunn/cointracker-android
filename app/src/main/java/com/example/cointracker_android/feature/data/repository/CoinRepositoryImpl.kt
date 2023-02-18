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
import com.google.firebase.firestore.ktx.toObject
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

        var localCoins = dao.getCoins(query)

        try {
            if (localCoins.isEmpty()) {
                val remoteCoins = api.getCoins()
                dao.insertCoins(remoteCoins.map { coinMapper.map(it) })
                localCoins = dao.getCoins(query)
            }
        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    message = "Ops, something went wrong!",
                    data = null
                )
            )
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    message = "Please, check your internet connection!",
                    data = null
                )
            )
        }

        emit(Resource.Success(data = localCoins))
    }
    override fun getCoinDetailById(id: String): Flow<Resource<List<CoinDetail>>> = flow {
        emit(Resource.Loading())

        try {
            val coinDetail = api.getCoinDetail(id)
            emit(
                Resource.Success(
                    data = coinDetail.map { coinMapper.map(it) }
                )
            )
        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    message = "Ops, something went wrong!",
                    data = null
                )
            )
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    message = "Please, check your internet connection!",
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
                .get()
                .addOnSuccessListener { snapshot ->
                    val documents = snapshot.documents.mapNotNull {
                        it.toObject(CoinDetail::class.java)
                    }
                    if (documents.isEmpty()) {
                        continuation.resume(null)
                    } else {
                        continuation.resume(documents)
                    }
                }
                .addOnFailureListener {
                    continuation.resume(null)
                }
        }
    }

    override suspend fun removeFavoriteCoin(coinId: String): String? {
        return suspendCancellableCoroutine { continuation ->
            val userId = auth.currentUser?.uid.orEmpty()
            firestore
                .collection(Constants.Firebase.COLLECTION_USERS)
                .document(userId)
                .collection(Constants.Firebase.COLLECTION_FAVORITES)
                .document(coinId)
                .delete()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        continuation.resume("Removed from your favorites.")
                    } else {
                        continuation.resume(null)
                    }
                }
        }
    }

    override suspend fun getCurrentPrice(coinId: String): Double? {
        val apiResult = api.getCoinDetail(coinId)
        return apiResult.firstOrNull()?.currentPrice
    }
}