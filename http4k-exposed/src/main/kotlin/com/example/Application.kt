package com.example

import com.example.config.initDB
import com.example.handlers.OfferingsHandler
import com.example.handlers.RatingsHandler
import org.http4k.core.Body
import org.http4k.core.HttpHandler
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.core.with
import org.http4k.format.Jackson.auto
import org.http4k.routing.bind
import org.http4k.routing.routes
import org.http4k.server.Netty
import org.http4k.server.asServer

fun main() {
    initDB()

    Application().handlers().asServer(Netty(8080)).start()
}

class Application {
    fun handlers(): HttpHandler {
        val offeringsHandler = OfferingsHandler()
        val ratingsHandler = RatingsHandler()

        return routes(
            "/health_check" bind Method.GET to healthCheck(),
            "/offerings" bind routes(
                "/" bind Method.GET to { offeringsHandler.index() },
                "/" bind Method.POST to offeringsHandler.create(),
                "/{offering_id}" bind routes(
                    "/" bind Method.GET to offeringsHandler.show(),
                    "/" bind Method.PUT to offeringsHandler.update(),
                    "/" bind Method.DELETE to offeringsHandler.delete(),

                    "/ratings" bind Method.GET to ratingsHandler.indexByOffering(),
                    "/ratings" bind Method.POST to ratingsHandler.create()
                )
            )
        )
    }

    private data class Health(val app: String = "UP")

    private fun healthCheck(): (Request) -> Response = {
        val healthLens = Body.auto<Health>().toLens()

        Response(Status.OK).with(healthLens of Health())
    }
}