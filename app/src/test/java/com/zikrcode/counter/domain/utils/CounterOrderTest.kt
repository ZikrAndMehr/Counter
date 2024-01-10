package com.zikrcode.counter.domain.utils

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CounterOrderTest {

    private val ascendingOrderType = OrderType.ASCENDING
    private val descendingOrderType = OrderType.DESCENDING

    @Test
    fun testCopy_withNameAscendingOrder() {
        val counterOrder = CounterOrder.Name(ascendingOrderType)
        val newCounterOrder = counterOrder.copy(orderType = descendingOrderType)

        assertEquals(descendingOrderType, newCounterOrder.orderType)
    }

    @Test
    fun testCopy_withNameDescendingOrder() {
        val counterOrder = CounterOrder.Name(descendingOrderType)
        val newCounterOrder = counterOrder.copy(orderType = ascendingOrderType)

        assertEquals(ascendingOrderType, newCounterOrder.orderType)
    }

    @Test
    fun testCopy_withDateAscendingOrder() {
        val counterOrder = CounterOrder.Date(ascendingOrderType)
        val newCounterOrder = counterOrder.copy(orderType = descendingOrderType)

        assertEquals(descendingOrderType, newCounterOrder.orderType)
    }

    @Test
    fun testCopy_withDateDescendingOrder() {
        val counterOrder = CounterOrder.Date(descendingOrderType)
        val newCounterOrder = counterOrder.copy(orderType = ascendingOrderType)

        assertEquals(ascendingOrderType, newCounterOrder.orderType)
    }
}