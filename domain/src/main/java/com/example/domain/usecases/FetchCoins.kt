package com.example.domain.usecases

import com.example.domain.base.CompletableUseCase
import com.example.domain.repositories.CoinsRepository
import javax.inject.Inject

class FetchCoins @Inject constructor(
    private val coinsRepository: CoinsRepository
) : CompletableUseCase<Unit>() {

    override fun buildUseCase(params: Unit?) = coinsRepository.fetchCoins()

}
