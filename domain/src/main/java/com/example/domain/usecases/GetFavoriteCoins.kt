package com.example.domain.usecases

import com.example.domain.base.SingleUseCase
import com.example.domain.models.Coin
import com.example.domain.models.CoinDetail
import com.example.domain.repositories.CoinsRepository
import javax.inject.Inject

class GetFavoriteCoins @Inject constructor(
    private val coinsRepository: CoinsRepository
) : SingleUseCase<List<Coin>, Unit>() {

    override fun buildUseCase(params: Unit?) = coinsRepository.getFavoriteCoins()

}
