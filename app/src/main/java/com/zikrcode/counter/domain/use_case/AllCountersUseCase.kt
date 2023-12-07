package com.zikrcode.counter.domain.use_case

import com.zikrcode.counter.domain.model.Counter
import com.zikrcode.counter.domain.repository.CounterRepository
import com.zikrcode.counter.domain.utils.CounterOrder
import com.zikrcode.counter.domain.utils.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AllCountersUseCase(
    private val counterRepository: CounterRepository
) {

    operator fun invoke(
        counterOrder: CounterOrder = CounterOrder.Date(OrderType.DESCENDING)
    ): Flow<List<Counter>> {
        return counterRepository.allCounters().map { counters ->
            when (counterOrder.orderType) {
                OrderType.ASCENDING -> {
                    when (counterOrder) {
                        is CounterOrder.Name -> counters.sortedBy { it.counterName }
                        is CounterOrder.Date -> counters.sortedBy { it.counterDate }
                    }
                }
                OrderType.DESCENDING -> {
                    when (counterOrder) {
                        is CounterOrder.Name -> counters.sortedByDescending { it.counterName }
                        is CounterOrder.Date -> counters.sortedByDescending { it.counterDate }
                    }
                }
            }
        }
    }
}