package com.example.data.models

import com.google.gson.annotations.SerializedName

data class CoinResponse(
    @SerializedName("id") val id: String,
    @SerializedName("symbol") val symbol: String,
    @SerializedName("name") val name: String,
)
