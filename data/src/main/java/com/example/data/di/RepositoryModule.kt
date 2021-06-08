package com.example.data.di

import com.example.data.repository.CoinsRepositoryImpl
import com.example.domain.repositories.CoinsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
interface RepositoryModule {

    @Singleton
    @Binds
    fun bindCoinsRepository(coinsRepositoryImpl: CoinsRepositoryImpl): CoinsRepository
}
