package com.zikrcode.counter.domain.use_case

import com.zikrcode.counter.R
import com.zikrcode.counter.domain.model.Counter
import com.zikrcode.counter.domain.repository.CounterRepository
import com.zikrcode.counter.domain.use_case.counter_validation.CounterValidationResult
import com.zikrcode.counter.presentation.utils.UiText

class InsertCounterUseCase(
    private val counterRepository: CounterRepository
) {

    suspend operator fun invoke(counter: Counter): CounterValidationResult {
        if (counter.counterName.isBlank()) {
            return CounterValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(R.string.empty_counter_name)
            )
        }
        counterRepository.insertCounter(counter)
        return CounterValidationResult(successful = true)
    }
}