package com.envylabs.cautiousengine.config

import io.micrometer.prometheus.PrometheusConfig
import io.micrometer.prometheus.PrometheusMeterRegistry

object PrometheusConfig {
    private val meterRegistry = PrometheusMeterRegistry(PrometheusConfig.DEFAULT).apply {
        this.Config().commonTags("application", "cautious-engine_ktor-kodein-postgres")
    }

    fun meterRegistry(): PrometheusMeterRegistry {
        return meterRegistry
    }
}
