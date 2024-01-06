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

package com.zikrcode.counter.utils

import com.zikrcode.counter.domain.model.Counter

val testCounters = listOf(
    Counter(
        id = 1,
        counterName = "Name",
        counterDescription = "Description",
        counterDate = 0L,
        counterSavedValue = 0
    ),
    Counter(
        id = 2,
        counterName = "Name",
        counterDescription = "Description",
        counterDate = 0L,
        counterSavedValue = 0
    ),
    Counter(
        id = 3,
        counterName = "Name",
        counterDescription = "Description",
        counterDate = 0L,
        counterSavedValue = 0
    )
)