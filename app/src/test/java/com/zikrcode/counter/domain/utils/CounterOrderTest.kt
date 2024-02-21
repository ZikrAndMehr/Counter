/*
 * Copyright (C) 2023 Zokirjon Mamadjonov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.zikrcode.counter.domain.utils

import org.junit.Assert.*
import org.junit.Test

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