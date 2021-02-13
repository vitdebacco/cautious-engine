package com.example

import com.example.config.initDB
import com.example.filters.ExceptionResolver
import com.example.handlers.OfferingsHandler
import com.example.handlers.RatingsHandler
import com.example.services.OfferingsServiceImpl
import io.micrometer.core.instrument.Meter
import io.micrometer.core.instrument.simple.SimpleMeterRegistry
import org.http4k.core.Body
import org.http4k.core.HttpHandler
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.core.then
import org.http4k.core.with
import org.http4k.filter.DebuggingFilters
import org.http4k.filter.MicrometerMetrics
import org.http4k.filter.ServerFilters
import org.http4k.format.Jackson.auto
import org.http4k.routing.bind
import org.http4k.routing.routes
import org.http4k.server.Netty
import org.http4k.server.asServer

// this is a micrometer registry used mostly for testing - substitute the correct implementation.
val registry = SimpleMeterRegistry()

fun main() {
    initDB()

    DebuggingFilters.PrintRequestAndResponse()
        .then(ServerFilters.MicrometerMetrics.RequestCounter(registry))
        .then(ServerFilters.MicrometerMetrics.RequestTimer(registry))
        .then(Application().handlers())
        .asServer(Netty(8080))
        .start()
}

class Application {
    fun handlers(): HttpHandler {

        // services
        val offeringsService = OfferingsServiceImpl()

        // handlers
        val offeringsHandler = OfferingsHandler(OfferingsServiceImpl())
        val ratingsHandler = RatingsHandler()

        return ExceptionResolver().then(
            routes(
                "/health_check" bind Method.GET to healthCheck(),
                "/metrics" bind Method.GET to metrics(),
                "/offerings" bind routes(
                    "/" bind Method.GET to offeringsHandler.index(),
                    "/" bind Method.POST to offeringsHandler.create(),
                    "/{offering_id}" bind routes(
                        "/" bind Method.GET to offeringsHandler.show(),
                        "/" bind Method.PUT to offeringsHandler.update(),
                        "/" bind Method.DELETE to offeringsHandler.delete(),

                        "/ratings" bind Method.GET to ratingsHandler.indexByOffering(),
                        "/ratings" bind Method.POST to ratingsHandler.create(),
                        "/ratings" bind Method.DELETE to ratingsHandler.delete()
                    )
                )
            )
        )
    }

    private data class Health(val app: String = "UP")
    private data class Metrics(val metrics: List<Meter>)

    private fun healthCheck(): (Request) -> Response = {
        val healthLens = Body.auto<Health>().toLens()

        Response(Status.OK).with(healthLens of Health())
    }

    private fun metrics(): (Request) -> Response = {
        val metricsLens = Body.auto<Metrics>().toLens()

        Response(Status.OK).with(metricsLens of Metrics(registry.meters))
    }
}