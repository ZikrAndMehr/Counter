package com.zikrcode.counter.presentation.counter_home

import com.zikrcode.counter.domain.model.Counter

sealed class CounterHomeEvent {

    data class Edit(val counter: Counter) : CounterHomeEvent()

    object Reset : CounterHomeEvent()

    object Increment : CounterHomeEvent()

    object Decrement : CounterHomeEvent()
}