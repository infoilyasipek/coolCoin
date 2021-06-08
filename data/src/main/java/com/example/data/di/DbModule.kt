package com.example.data.di

import android.content.Context
import androidx.room.Room
import com.example.data.locale.CoinsDao
import com.example.data.locale.CoolCoinDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DbModule {

    @Provides
    @Singleton
    fun provideDb(@ApplicationContext context: Context): CoolCoinDb {
        return Room
            .databaseBuilder(context, CoolCoinDb::class.java, "coins.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideCoinsDao(db: CoolCoinDb): CoinsDao {
        return db.coinsDao()
    }
}
