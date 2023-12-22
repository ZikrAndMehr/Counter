package com.zikrcode.counter.data.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zikrcode.counter.domain.model.Counter
import kotlinx.coroutines.flow.Flow

@Dao
interface CounterDao {

    @Query("SELECT * FROM counter WHERE id = :id")
    fun counterById(id: Int): Flow<Counter>

    @Query("SELECT * FROM counter")
    fun allCounters(): Flow<List<Counter>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCounter(counter: Counter)

    @Delete
    suspend fun deleteCounter(counter: Counter)
}