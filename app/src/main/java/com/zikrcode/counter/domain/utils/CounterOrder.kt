package com.zikrcode.counter.domain.utils

sealed class CounterOrder(val orderType: OrderType) {

    class Name(orderType: OrderType) : CounterOrder(orderType)

    class Date(orderType: OrderType) : CounterOrder(orderType)

    fun copy(orderType: OrderType): CounterOrder {
        return when(this) {
            is Name -> Name(orderType)
            is Date -> Date(orderType)
        }
    }
}