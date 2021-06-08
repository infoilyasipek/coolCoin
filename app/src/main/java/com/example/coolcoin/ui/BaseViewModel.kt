package com.example.coolcoin.ui

import androidx.lifecycle.ViewModel
import com.example.domain.base.UseCaseCallback
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    fun <T> simpleCallback(
        onComplete: (() -> Unit)? = null,
        onStart: (() -> Unit)? = null,
        onError: ((throwable: Throwable) -> Unit)? = null,
        onResponse: ((response: T) -> Unit)? = null,
    ) = object : UseCaseCallback<T> {
        override fun onStart() {
            onStart?.invoke()
        }

        override fun onComplete() {
            onComplete?.invoke()
        }

        override fun onResponse(response: T) {
            onResponse?.invoke(response)
        }

        override fun onError(throwable: Throwable) {
            onError?.invoke(throwable)
        }
    }

}
