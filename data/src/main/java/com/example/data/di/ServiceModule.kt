package com.example.data.di

import com.example.data.remote.CoinsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object ServiceModule {

    @Singleton
    @Provides
    fun provideCoinsService(retrofit: Retrofit): CoinsService {
        return retrofit.create(CoinsService::class.java)
    }
}
