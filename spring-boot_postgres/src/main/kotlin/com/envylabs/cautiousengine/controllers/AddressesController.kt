package com.envylabs.cautiousengine.controllers

import com.envylabs.cautiousengine.models.Address
import com.envylabs.cautiousengine.models.api.Addresses
import com.envylabs.cautiousengine.services.AddressService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/addresses")
class AddressesController(
    private val addressService: AddressService
) {

    @GetMapping
    fun index(): Addresses {
        val addresses = addressService.many()

        return Addresses(
            addresses = addresses,
            count = addresses.size
        )
    }

    @GetMapping("/{id}")
    fun show(@PathVariable id: String): Address {
        // ignore [id] since we're randomly generating this data

        return addressService.one()
    }
}
