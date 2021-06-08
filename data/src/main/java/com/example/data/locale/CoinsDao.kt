package com.example.data.locale

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.models.CoinEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
abstract class CoinsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(coins: List<CoinEntity>): Completable

    @Query("SELECT *FROM coins WHERE symbol = :searchQuery OR name LIKE :searchQuery")
    abstract fun search(searchQuery: String): Single<List<CoinEntity>>

}
