package com.example.data.locale

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.models.CoinEntity

@Database(
    entities = [
        CoinEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class CoolCoinDb : RoomDatabase() {

    abstract fun coinsDao(): CoinsDao

}
