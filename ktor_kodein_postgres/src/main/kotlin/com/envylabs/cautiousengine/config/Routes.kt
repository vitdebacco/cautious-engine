package com.envylabs.cautiousengine.config

import com.envylabs.cautiousengine.handlers.AddressesHandler
import com.envylabs.cautiousengine.services.faker.AddressServiceImpl
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.route

val addressesHandler = AddressesHandler(addressService = AddressServiceImpl())

fun Routing.routes() {
    route("/") {
        get("actuator/metrics") { call.respondText(PrometheusConfig.meterRegistry().scrape()) }
        get("addresses") { call.respond(HttpStatusCode.OK, addressesHandler.index()) }
        get("addresses/{id}") { call.respond(HttpStatusCode.OK, addressesHandler.show()) }
    }
}
