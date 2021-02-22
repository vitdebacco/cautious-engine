package com.envylabs.cautiousengine.handlers

import com.envylabs.cautiousengine.models.Address
import com.envylabs.cautiousengine.models.api.Addresses
import com.envylabs.cautiousengine.services.AddressService

class AddressesHandler(
    private val addressService: AddressService
) {

    fun index(): Addresses {
        val addresses = addressService.many()

        return Addresses(
            addresses = addresses,
            count = addresses.size
        )
    }

    fun show(): Address {
        // ignore [id] since we're randomly generating this data

        return addressService.one()
    }
}
