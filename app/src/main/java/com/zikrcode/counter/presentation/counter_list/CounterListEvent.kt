package com.zikrcode.counter.presentation.counter_list

import com.zikrcode.counter.domain.model.Counter
import com.zikrcode.counter.domain.utils.CounterOrder

sealed class CounterListEvent {

    object ToggleOrderSection : CounterListEvent()

    data class Order(val counterOrder: CounterOrder) : CounterListEvent()

    data class SelectCounter(val counter: Counter) : CounterListEvent()

    data class Delete(val counter: Counter) : CounterListEvent()

    data class Edit(val counter: Counter) : CounterListEvent()

    object NewCounter : CounterListEvent()

    object RestoreCounter : CounterListEvent()
}
