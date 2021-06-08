package com.example.data.mappers

import com.example.data.models.CoinEntity
import com.example.data.models.CoinFireStoreEntity
import com.example.data.models.CoinResponse
import com.example.domain.models.Coin
import javax.inject.Inject

class CoinsMapper @Inject constructor() {

    fun mapToDbEntityItems(coins: List<CoinResponse>): List<CoinEntity> {
        return coins.map {
            CoinEntity(
                id = it.id,
                symbol = it.symbol,
                name = it.name
            )
        }
    }

    fun mapEntitiesToDomainItems(coins: List<CoinEntity>): List<Coin> {
        return coins.map {
            Coin(
                id = it.id,
                symbol = it.symbol,
                name = it.name
            )
        }
    }

    fun mapToFireStoreEntity(coin: Coin, userId: String): CoinFireStoreEntity {
        return CoinFireStoreEntity(
            coinId = coin.id,
            symbol = coin.symbol,
            name = coin.name,
            userId = userId
        )
    }

    fun mapFireStoreEntitiesToDomainItems(coins: List<CoinFireStoreEntity>): List<Coin> {
        return coins.map {
            Coin(
                id = it.coinId,
                symbol = it.symbol,
                name = it.name
            )
        }
    }
}
