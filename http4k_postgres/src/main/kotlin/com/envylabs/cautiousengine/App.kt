package com.envylabs.cautiousengine

import com.envylabs.cautiousengine.config.PrometheusConfig
import com.envylabs.cautiousengine.handlers.AddressesHandler
import com.envylabs.cautiousengine.services.faker.AddressServiceImpl
import org.http4k.core.HttpHandler
import org.http4k.core.Method
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.core.then
import org.http4k.filter.MicrometerMetrics
import org.http4k.filter.ServerFilters
import org.http4k.routing.bind
import org.http4k.routing.routes
import org.http4k.server.Undertow
import org.http4k.server.asServer

fun main() {
    ServerFilters.MicrometerMetrics.RequestCounter(meterRegistry)
        .then(ServerFilters.MicrometerMetrics.RequestTimer(meterRegistry))
        .then(App().handlers())
        .asServer(Undertow(8080))
        .start()
}

class App {
    fun handlers(): HttpHandler {
        val addressService = AddressServiceImpl()
        val addressesHandler = AddressesHandler(addressService = addressService)

        return routes(
            "/metrics" bind Method.GET to { Response(Status.OK).body(meterRegistry.scrape()) },
            "/addresses" bind routes(
                "/" bind Method.GET to addressesHandler.index(),
                "/{id}" bind routes(
                    "/" bind Method.GET to addressesHandler.show()
                )
            )
        )
    }
}

val meterRegistry = PrometheusConfig.meterRegistry()
