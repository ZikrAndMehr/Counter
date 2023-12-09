package com.zikrcode.counter.domain.use_case

import com.zikrcode.counter.domain.model.Counter
import com.zikrcode.counter.domain.repository.CounterRepository

class CounterByIdUseCase(
    private val counterRepository: CounterRepository
) {

    suspend operator fun invoke(id: Int): Counter? {
        return counterRepository.counterById(id)
    }
}