package com.example.domain.base

interface UseCaseCallback<T> {
    fun onStart()
    fun onComplete()
    fun onResponse(response: T)
    fun onError(throwable: Throwable)
}
