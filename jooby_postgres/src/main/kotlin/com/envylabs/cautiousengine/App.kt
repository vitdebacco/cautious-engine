package com.envylabs.cautiousengine

import com.envylabs.cautiousengine.config.JacksonConfig
import com.envylabs.cautiousengine.controllers.AddressesController
import com.envylabs.cautiousengine.services.faker.AddressServiceImpl
import io.jooby.Kooby
import io.jooby.json.JacksonModule
import io.jooby.runApp

class App : Kooby({

    install(JacksonModule(JacksonConfig().objectMapper()))

    // TODO: move to factories
    val addressService = AddressServiceImpl()
    val addressesController = AddressesController(addressService = addressService)

    coroutine {
        get("/health_check") { AppStatus() }
        get("/addresses") { addressesController.index() }
        get("/addresses/{id}") { addressesController.show(ctx) }
    }
})

fun main(args: Array<String>) {
    runApp(args, App::class)
}

data class AppStatus(val app: String = "up")
