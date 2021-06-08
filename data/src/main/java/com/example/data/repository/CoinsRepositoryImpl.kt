package com.example.data.repository

import com.example.data.locale.CoinsDao
import com.example.data.mappers.CoinDetailMapper
import com.example.data.mappers.CoinsMapper
import com.example.data.models.CoinFireStoreEntity
import com.example.data.remote.CoinsService
import com.example.domain.models.Coin
import com.example.domain.models.CoinDetail
import com.example.domain.repositories.CoinsRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class CoinsRepositoryImpl @Inject constructor(
    private val coinsService: CoinsService,
    private val coinsDao: CoinsDao,
    private val coinsMapper: CoinsMapper,
    private val coinDetailMapper: CoinDetailMapper
) : CoinsRepository {

    init {
        FirebaseFirestore.setLoggingEnabled(true)
    }

    private val userId: String
        get() = FirebaseAuth.getInstance().currentUser?.uid!!

    private val favoritesCollection = Firebase.firestore
        .collection("favorites")

    override fun fetchCoins(): Completable {
        return coinsService.getCoinList()
            .flatMapCompletable() {
                coinsDao.insert(coinsMapper.mapToDbEntityItems(it))
            }
    }

    override fun search(searchQuery: String): Single<List<Coin>> {
        return coinsDao.search(searchQuery)
            .map {
                coinsMapper.mapEntitiesToDomainItems(it)
            }
    }

    override fun getCoinDetail(id: String): Single<CoinDetail> {
        return coinsService.getCoinDetail(id)
            .map {
                coinDetailMapper.mapToDomainItem(it)
            }
    }

    override fun getFavoriteCoins(): Single<List<Coin>> {
        return Single.create { emitter ->
            favoritesCollection
                .whereEqualTo("userId", userId)
                .get()
                .addOnSuccessListener {
                    emitter.onSuccess(
                        coinsMapper.mapFireStoreEntitiesToDomainItems(
                            it.toObjects(CoinFireStoreEntity::class.java)
                        )
                    )
                }
                .addOnFailureListener {
                    emitter.onError(it)
                }
        }
    }

    override fun removeCoinFromFavorite(coinId: String): Completable {
        return Completable.create { emitter ->
            favoritesCollection
                .document(userId + coinId)
                .delete()
                .addOnSuccessListener {
                    emitter.onComplete()
                }.addOnFailureListener {
                    emitter.onError(it)
                }
        }
    }

    override fun addCoinToFavorites(coin: Coin): Completable {
        return Completable.create { emitter ->
            favoritesCollection
                .document(userId + coin.id)
                .set(coinsMapper.mapToFireStoreEntity(coin, userId))
                .addOnSuccessListener {
                    emitter.onComplete()
                }.addOnFailureListener {
                    emitter.onError(it)
                }
        }
    }

    override fun isFavoriteCoin(coinId: String): Single<Boolean> {
        return Single.create { emitter ->
            favoritesCollection
                .document(userId + coinId)
                .get()
                .addOnSuccessListener {
                    emitter.onSuccess(it.exists())
                }.addOnFailureListener {
                    emitter.onError(it)
                }
        }
    }

}
