package com.example.domain.usecases

import com.example.domain.base.CompletableUseCase
import com.example.domain.repositories.CoinsRepository
import javax.inject.Inject

class RemoveCoinFromFavorite @Inject constructor(
    private val coinsRepository: CoinsRepository
) : CompletableUseCase<String>() {

    override fun buildUseCase(params: String?) = coinsRepository.removeCoinFromFavorite(params!!)

}
