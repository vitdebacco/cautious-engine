package com.envylabs.cautiousengine.handlers

import com.envylabs.cautiousengine.config.JacksonConfig.auto
import com.envylabs.cautiousengine.models.Address
import com.envylabs.cautiousengine.models.api.Addresses
import com.envylabs.cautiousengine.services.AddressService
import org.http4k.core.Body
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.lens.Path
import org.http4k.lens.nonEmptyString

class AddressesHandler(
    private val addressService: AddressService
) {

    private val idLens = Path.nonEmptyString().of("id")

    fun index() = { _: Request ->
        val addressesLens = Body.auto<Addresses>().toLens()
        val addresses = addressService.many()

        val response = Addresses(
            addresses = addresses,
            count = addresses.size
        )

        addressesLens(response, Response(Status.OK))
    }

    fun show() = { request: Request ->
        // ignore [id] since we're randomly generating this data
        val id = idLens(request)

        val addressLens = Body.auto<Address>().toLens()

        val address = addressService.one()
        addressLens(address, Response(Status.OK))
    }
}
