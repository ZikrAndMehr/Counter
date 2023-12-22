package com.zikrcode.counter.domain.use_case

import com.zikrcode.counter.domain.model.Counter
import com.zikrcode.counter.domain.repository.CounterRepository
import kotlinx.coroutines.flow.Flow

class CounterByIdUseCase(
    private val counterRepository: CounterRepository
) {

    operator fun invoke(id: Int): Flow<Counter> {
        return counterRepository.counterById(id)
    }
}