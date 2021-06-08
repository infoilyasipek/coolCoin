package com.example.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CoinDetail(
    val hashingAlgorithm: String,
    val description: String,
    val imageUrl: String,
    val currentPrice: Double,
    val priceChangePercentageIn24H: Double,
) : Parcelable
