package com.example.domain.repositories

import com.example.domain.models.Coin
import com.example.domain.models.CoinDetail
import io.reactivex.Completable
import io.reactivex.Single

interface CoinsRepository {

    fun fetchCoins(): Completable

    fun search(searchQuery: String): Single<List<Coin>>

    fun getCoinDetail(id: String): Single<CoinDetail>

    fun getFavoriteCoins(): Single<List<Coin>>

    fun removeCoinFromFavorite(coinId: String): Completable

    fun addCoinToFavorites(coin: Coin): Completable

    fun isFavoriteCoin(coinId: String): Single<Boolean>

}
