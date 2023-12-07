package com.zikrcode.counter.domain.use_case

import com.zikrcode.counter.domain.model.Counter
import com.zikrcode.counter.domain.repository.CounterRepository

class CounterByNameUseCase(
    private val counterRepository: CounterRepository
) {

    suspend operator fun invoke(counterName: String): Counter? {
        return counterRepository.counterByName(counterName)
    }
}