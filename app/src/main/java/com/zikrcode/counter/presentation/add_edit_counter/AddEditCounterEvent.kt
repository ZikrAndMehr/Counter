package com.zikrcode.counter.presentation.add_edit_counter

sealed class AddEditCounterEvent {

    data class EnteredName(val value: String) : AddEditCounterEvent()

    data class EnteredValue(val value: String) : AddEditCounterEvent()

    data class EnteredDescription(val value: String) : AddEditCounterEvent()

    object GoBack : AddEditCounterEvent()

    object Cancel : AddEditCounterEvent()

    object Save : AddEditCounterEvent()
}