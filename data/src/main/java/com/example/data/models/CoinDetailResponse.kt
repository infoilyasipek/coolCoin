package com.example.data.models

import com.google.gson.annotations.SerializedName

data class CoinDetailResponse(
    @SerializedName("hashing_algorithm") val hashingAlgorithm: String?,
    @SerializedName("description") val descriptions: Map<String, String>,
    @SerializedName("image") val images: Map<String, String>,
    @SerializedName("market_data") val marketData: MarketData
) {

    data class MarketData(
        @SerializedName("current_price") val prices: Map<String, Double>,
        @SerializedName("price_change_percentage_24h") val priceChangePercentageIn24H: Double
    )
}
