package com.zikrcode.counter.domain.use_case

data class CounterUseCases(
    val counterByNameUseCase: CounterByNameUseCase,
    val allCountersUseCase: AllCountersUseCase,
    val insertCounterUseCase: InsertCounterUseCase,
    val deleteCounterUseCase: DeleteCounterUseCase
)
