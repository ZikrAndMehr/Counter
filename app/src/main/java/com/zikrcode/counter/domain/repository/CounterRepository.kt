package com.zikrcode.counter.domain.repository

import com.zikrcode.counter.domain.model.Counter
import kotlinx.coroutines.flow.Flow

interface CounterRepository {

    suspend fun counterByName(counterName: String): Counter?

    fun allCounters(): Flow<List<Counter>>

    suspend fun insertCounter(counter: Counter)

    suspend fun deleteCounter(counter: Counter)
}