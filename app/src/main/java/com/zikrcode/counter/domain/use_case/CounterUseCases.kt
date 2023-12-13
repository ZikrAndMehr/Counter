package com.zikrcode.counter.domain.use_case

data class CounterUseCases(
    val counterByIdUseCase: CounterByIdUseCase,
    val allCountersUseCase: AllCountersUseCase,
    val insertCounterUseCase: InsertCounterUseCase,
    val deleteCounterUseCase: DeleteCounterUseCase,
    val readUserPreferenceUseCase: ReadUserPreferenceUseCase,
    val writeUserPreferenceUseCase: WriteUserPreferenceUseCase
)
