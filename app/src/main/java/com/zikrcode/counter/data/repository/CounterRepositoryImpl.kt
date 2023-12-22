package com.zikrcode.counter.data.repository

import com.zikrcode.counter.data.data_source.CounterDao
import com.zikrcode.counter.domain.model.Counter
import com.zikrcode.counter.domain.repository.CounterRepository
import kotlinx.coroutines.flow.Flow

class CounterRepositoryImpl(
    private val counterDao: CounterDao
) : CounterRepository {

    override fun counterById(id: Int): Flow<Counter> {
        return counterDao.counterById(id)
    }

    override fun allCounters(): Flow<List<Counter>> {
        return counterDao.allCounters()
    }

    override suspend fun insertCounter(counter: Counter) {
        counterDao.insertCounter(counter)
    }

    override suspend fun deleteCounter(counter: Counter) {
        counterDao.deleteCounter(counter)
    }
}