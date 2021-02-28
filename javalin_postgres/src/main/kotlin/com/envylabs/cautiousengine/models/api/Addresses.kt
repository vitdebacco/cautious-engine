package com.envylabs.cautiousengine.models.api

import com.envylabs.cautiousengine.models.Address

data class Addresses(
    val addresses: List<Address>,
    val count: Int
)
