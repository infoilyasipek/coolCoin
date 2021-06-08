package com.example.coolcoin.ui.home

import androidx.lifecycle.MutableLiveData
import com.example.coolcoin.ui.BaseViewModel
import com.example.domain.models.Coin
import com.example.domain.models.Status
import com.example.domain.usecases.FetchCoins
import com.example.domain.usecases.SearchCoins
import com.hadilq.liveevent.LiveEvent
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val fetchCoins: FetchCoins,
    private val searchCoins: SearchCoins
) : BaseViewModel() {

    val fetchCoinsStatus = LiveEvent<Status>()
    val searchResults = MutableLiveData<List<Coin>>()

    fun fetchCoins() {
        fetchCoins(
            callback = simpleCallback(
                onStart = {
                    fetchCoinsStatus.value = Status.LOADING
                },
                onResponse = {
                    fetchCoinsStatus.value = Status.SUCCESS
                },
                onError = {
                    fetchCoinsStatus.value = Status.ERROR
                }
            )
        ).also {
            addDisposable(it)
        }
    }

    fun searchCoins(searchQuery: String) {
        searchCoins(
            params = searchQuery,
            callback = simpleCallback(
                onResponse = {
                    searchResults.value = it
                }
            )
        ).also {
            addDisposable(it)
        }
    }

}
