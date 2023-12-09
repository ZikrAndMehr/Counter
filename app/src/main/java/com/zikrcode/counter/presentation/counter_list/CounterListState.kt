package com.zikrcode.counter.presentation.counter_list

import com.zikrcode.counter.domain.model.Counter
import com.zikrcode.counter.domain.utils.CounterOrder
import com.zikrcode.counter.domain.utils.OrderType

data class CounterListState(
    val allCounters: List<Counter> = emptyList(),
    val counterOrder: CounterOrder = CounterOrder.Date(OrderType.DESCENDING),
    val isOrderSectionVisible: Boolean = false
)