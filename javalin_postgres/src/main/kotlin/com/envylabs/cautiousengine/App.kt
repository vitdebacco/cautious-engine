package com.envylabs.cautiousengine

import com.envylabs.cautiousengine.config.JacksonConfig
import com.envylabs.cautiousengine.config.PrometheusConfig
import com.envylabs.cautiousengine.controllers.AddressesController
import com.envylabs.cautiousengine.services.faker.AddressServiceImpl
import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.get
import io.javalin.plugin.json.JavalinJackson
import io.javalin.plugin.metrics.MicrometerPlugin
import io.prometheus.client.exporter.common.TextFormat

fun main(args: Array<String>) {
    JavalinJackson.configure(JacksonConfig.defaultObjectMapper())

    // Dependencies
    val meterRegistry = PrometheusConfig.meterRegistry()
    val addressesController = AddressesController(addressService = AddressServiceImpl())

    val app = Javalin.create {
        it.registerPlugin(MicrometerPlugin(meterRegistry))
    }.start(8080)

    app.routes {
        get("/addresses") {
            it.json(addressesController.index())
        }
        get("/addresses/:id") {
            val id = it.pathParam("id")
            it.json(addressesController.show(id))
        }
        get("/metrics") {
            it.contentType(TextFormat.CONTENT_TYPE_004).result(meterRegistry.scrape())
        }
    }
}
