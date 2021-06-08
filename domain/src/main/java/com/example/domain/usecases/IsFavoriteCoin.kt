package com.example.domain.usecases

import com.example.domain.base.SingleUseCase
import com.example.domain.models.Coin
import com.example.domain.repositories.CoinsRepository
import javax.inject.Inject

class IsFavoriteCoin @Inject constructor(
    private val coinsRepository: CoinsRepository
) : SingleUseCase<Boolean, String>() {

    override fun buildUseCase(params: String?) = coinsRepository.isFavoriteCoin(params!!)

}
