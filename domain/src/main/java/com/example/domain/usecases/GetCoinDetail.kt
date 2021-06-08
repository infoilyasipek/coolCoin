package com.example.domain.usecases

import com.example.domain.base.SingleUseCase
import com.example.domain.models.CoinDetail
import com.example.domain.repositories.CoinsRepository
import javax.inject.Inject

class GetCoinDetail @Inject constructor(
    private val coinsRepository: CoinsRepository
) : SingleUseCase<CoinDetail, String>() {

    override fun buildUseCase(params: String?) = coinsRepository.getCoinDetail(id = params!!)

}
