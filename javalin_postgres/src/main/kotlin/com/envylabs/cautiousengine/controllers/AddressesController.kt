package com.envylabs.cautiousengine.controllers

import com.envylabs.cautiousengine.models.Address
import com.envylabs.cautiousengine.models.api.Addresses
import com.envylabs.cautiousengine.services.AddressService

class AddressesController(
    private val addressService: AddressService
) {

    fun index(): Addresses {
        val addresses = addressService.many()

        return Addresses(
            addresses = addresses,
            count = addresses.size
        )
    }

    fun show(id: String): Address {
        // ignore [id] since we're randomly generating this data

        return addressService.one()
    }
}
