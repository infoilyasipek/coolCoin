package com.example.domain.usecases

import com.example.domain.base.CompletableUseCase
import com.example.domain.models.Coin
import com.example.domain.repositories.CoinsRepository
import javax.inject.Inject

class AddCoinToFavorites @Inject constructor(
    private val coinsRepository: CoinsRepository
) : CompletableUseCase<Coin>() {

    override fun buildUseCase(params: Coin?) = coinsRepository.addCoinToFavorites(params!!)

}
