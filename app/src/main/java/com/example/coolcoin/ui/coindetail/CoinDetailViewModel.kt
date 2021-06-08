package com.example.coolcoin.ui.coindetail

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.MutableLiveData
import com.example.coolcoin.ui.BaseViewModel
import com.example.coolcoin.util.UserManager
import com.example.domain.models.Coin
import com.example.domain.models.CoinDetail
import com.example.domain.usecases.AddCoinToFavorites
import com.example.domain.usecases.GetCoinDetail
import com.example.domain.usecases.IsFavoriteCoin
import com.example.domain.usecases.RemoveCoinFromFavorite
import javax.inject.Inject

class CoinDetailViewModel @Inject constructor(
    private val getCoinDetail: GetCoinDetail,
    private val isFavoriteCoinUseCase: IsFavoriteCoin,
    private val addCoinToFavorites: AddCoinToFavorites,
    private val removeCoinFromFavorite: RemoveCoinFromFavorite,
    private val userManager: UserManager
) : BaseViewModel() {

    val coinDetail = MutableLiveData<CoinDetail>()
    val isFavoriteCoin = MutableLiveData(false)

    fun getIsFavoriteCoin(coinId: String) {
        if (!userManager.isSignedIn) return
        isFavoriteCoinUseCase(
            params = coinId,
            callback = simpleCallback(
                onResponse = {
                    isFavoriteCoin.value = it
                },
            )
        ).also {
            addDisposable(it)
        }
    }

    fun getCoinDetail(id: String) {
        getCoinDetail(
            params = id,
            callback = simpleCallback(
                onResponse = {

                    coinDetail.value = it
                },
                onComplete = {
                    Handler(Looper.getMainLooper()).postDelayed({
                        getCoinDetail(id)
                    }, RE_FETCH_COIN_DETAIL_DELAY)
                },
            )
        ).also {
            addDisposable(it)
        }
    }

    fun updateFavoriteStatus(coin: Coin) {
        if (isFavoriteCoin.value == true) {
            removeCoinFromFavorite(coinId = coin.id)
        } else {
            addCoinToFavorites(coin)
        }
    }

    private fun addCoinToFavorites(coin: Coin) {
        addCoinToFavorites(
            params = coin,
            callback = simpleCallback {
                isFavoriteCoin.value = true
            }
        )
    }

    private fun removeCoinFromFavorite(coinId: String) {
        removeCoinFromFavorite(
            params = coinId,
            callback = simpleCallback {
                isFavoriteCoin.value = false
            }
        )
    }

    companion object {
        private const val RE_FETCH_COIN_DETAIL_DELAY = 3000L
    }

}
