package com.example.domain.base

import io.reactivex.Completable
import io.reactivex.disposables.Disposable

abstract class CompletableUseCase<Params> : BaseUseCase<Unit, Params>() {

    abstract fun buildUseCase(params: Params?): Completable

    override fun execute(
        params: Params?,
        callback: UseCaseCallback<Unit>?
    ): Disposable {
        return buildUseCase(params)
            .observeOn(postExecutionThread)
            .subscribeOn(executionThread)
            .doOnSubscribe {
                callback?.onStart()
            }
            .doOnComplete {
                callback?.onResponse(Unit)
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

