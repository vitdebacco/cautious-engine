package com.envylabs.cautiousengine

import io.micronaut.runtime.Micronaut.build

fun main(args: Array<String>) {
    build()
        .args(*args)
        .packages("com.envylabs.cautiousengine")
        .start()
}
