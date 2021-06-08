package com.example.domain.base

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

abstract class BaseUseCase<T, Params> {

    protected val postExecutionThread: Scheduler = AndroidSchedulers.mainThread()

    protected val executionThread: Scheduler = Schedulers.io()

    operator fun invoke(
        params: Params? = null,
        callback: UseCaseCallback<T>? = null
    ) = execute(params, callback)

    abstract fun execute(
        params: Params? = null,
        callback: UseCaseCallback<T>? = null
    ): Disposable

}
