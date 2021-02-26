package com.envylabs.cautiousengine.controllers

import com.envylabs.cautiousengine.models.Address
import com.envylabs.cautiousengine.models.api.Addresses
import com.envylabs.cautiousengine.services.AddressService
import javax.ws.rs.Consumes
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path("/addresses")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class AddressesController(
    private val addressService: AddressService
) {

    @GET
    fun index(): Addresses {
        val addresses = addressService.many()

        return Addresses(
            addresses = addresses,
            count = addresses.size
        )
    }

    @GET
    @Path("/{id}")
    fun show(@PathParam("id") id: String): Address {
        // ignore [id] since we're randomly generating this data

        return addressService.one()
    }
}
