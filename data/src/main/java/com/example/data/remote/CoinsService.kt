package com.example.data.remote

import com.example.data.models.CoinDetailResponse
import com.example.data.models.CoinResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinsService {

    @GET("coins/list")
    fun getCoinList(): Single<List<CoinResponse>>

    @GET("coins/{id}")
    fun getCoinDetail(@Path("id") id: String): Single<CoinDetailResponse>

}
