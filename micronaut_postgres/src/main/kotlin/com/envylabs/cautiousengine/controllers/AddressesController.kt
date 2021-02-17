package com.envylabs.cautiousengine.controllers

import com.envylabs.cautiousengine.models.Address
import com.envylabs.cautiousengine.models.api.Addresses
import com.envylabs.cautiousengine.services.AddressService
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Produces

@Controller("/addresses")
class AddressesController(
    private val addressService: AddressService
) {

    @Get("/")
    @Produces(MediaType.APPLICATION_JSON)
    fun index(): Addresses {
        val addresses = addressService.many()

        return Addresses(addresses = addresses, count = addresses.size)
    }

    @Get("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    fun show(@PathVariable id: String): Address {
        // ignore [id] since we're randomly generating this data

        return addressService.one()
    }
}
