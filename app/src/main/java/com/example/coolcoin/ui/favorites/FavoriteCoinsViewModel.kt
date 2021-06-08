package com.example.coolcoin.ui.favorites

import androidx.lifecycle.MutableLiveData
import com.example.coolcoin.ui.BaseViewModel
import com.example.domain.models.Coin
import com.example.domain.models.Resource
import com.example.domain.usecases.GetFavoriteCoins
import javax.inject.Inject

class FavoriteCoinsViewModel @Inject constructor(
    private val getFavoriteCoins: GetFavoriteCoins
) : BaseViewModel() {

    val favoriteCoins = MutableLiveData<Resource<List<Coin>>>()

    fun getFavoriteCoins() {
        getFavoriteCoins(
            callback = simpleCallback(
                onResponse = {
                    favoriteCoins.value = Resource.success(it)
                },
                onStart = {
                    favoriteCoins.value = Resource.loading()
                },
                onError = {
                    favoriteCoins.value = Resource.error(it.message ?: "")
                }
            )
        ).also {
            addDisposable(it)
        }
    }

}
