package com.envylabs.cautiousengine.services

import com.envylabs.cautiousengine.models.Address

interface AddressService {
    fun many(limit: Int = 100): List<Address>
    fun one(): Address
}
