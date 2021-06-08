package com.example.domain.base

import io.reactivex.Single
import io.reactivex.disposables.Disposable

abstract class SingleUseCase<T, Params> : BaseUseCase<T, Params>() {

    abstract fun buildUseCase(params: Params?): Single<T>

    override fun execute(
        params: Params?,
        callback: UseCaseCallback<T>?
    ): Disposable {
        return buildUseCase(params)
            .observeOn(postExecutionThread)
            .subscribeOn(executionThread)
            .doOnSubscribe {
                callback?.onStart()
            }
            .doOnSuccess {
                callback?.onResponse(it)
            }
            .doOnError {
                callback?.onError(it)
            }
            .doFinally {
                callback?.onComplete()
            }
            .subscribe({}, {})
    }

}

