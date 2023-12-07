package com.zikrcode.counter.domain.use_case.counter_validation

import com.zikrcode.counter.presentation.utils.UiText

data class CounterValidationResult(
    val successful: Boolean,
    val errorMessage: UiText? = null
)
