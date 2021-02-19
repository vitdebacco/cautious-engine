package com.envylabs.cautiousengine.controllers

import com.envylabs.cautiousengine.models.Address
import com.envylabs.cautiousengine.models.api.Addresses
import com.envylabs.cautiousengine.services.AddressService
import io.jooby.Context

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

    fun show(ctx: Context): Address {
        // ignore [id] since we're randomly generating this data

        return addressService.one()
    }
}
