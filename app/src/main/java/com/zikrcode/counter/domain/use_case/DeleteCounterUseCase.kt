package com.zikrcode.counter.domain.use_case

import com.zikrcode.counter.domain.model.Counter
import com.zikrcode.counter.domain.repository.CounterRepository

class DeleteCounterUseCase(
    private val counterRepository: CounterRepository
) {

    suspend operator fun invoke(counter: Counter) {
        counterRepository.deleteCounter(counter)
    }
}