package com.example.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Coin(
    val id: String,
    val symbol: String,
    val name: String,
) : Parcelable
