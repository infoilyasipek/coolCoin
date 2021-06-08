package com.example.data.models

import com.google.firebase.firestore.PropertyName

data class CoinFireStoreEntity(
    @PropertyName("coinId") val coinId: String = "",
    @PropertyName("symbol") val symbol: String = "",
    @PropertyName("name") val name: String = "",
    @PropertyName("userId") val userId: String = ""
)
