package com.example.data.mappers

import com.example.data.models.CoinDetailResponse
import com.example.domain.models.CoinDetail
import javax.inject.Inject

class CoinDetailMapper @Inject constructor() {

    fun mapToDomainItem(coinDetailResponse: CoinDetailResponse): CoinDetail {
        return CoinDetail(
            hashingAlgorithm = coinDetailResponse.hashingAlgorithm ?: "",
            description = coinDetailResponse.descriptions["en"] ?: "",
            imageUrl = coinDetailResponse.images["small"]!!,
            currentPrice = coinDetailResponse.marketData.prices["usd"]!!,
            priceChangePercentageIn24H = coinDetailResponse.marketData.priceChangePercentageIn24H,
        )
    }
}
