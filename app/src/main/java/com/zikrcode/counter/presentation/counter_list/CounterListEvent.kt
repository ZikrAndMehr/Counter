package com.zikrcode.counter.presentation.counter_list

import com.zikrcode.counter.domain.model.Counter
import com.zikrcode.counter.domain.utils.CounterOrder

sealed class CounterListEvent {

    data class Order(val counterOrder: CounterOrder) : CounterListEvent()

    data class Delete(val counter: Counter) : CounterListEvent()

    object RestoreCounter : CounterListEvent()

    object ToggleOrderSection : CounterListEvent()
}
