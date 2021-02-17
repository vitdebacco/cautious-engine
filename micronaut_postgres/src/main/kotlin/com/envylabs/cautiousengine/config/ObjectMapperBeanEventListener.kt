package com.envylabs.cautiousengine.config

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import io.micronaut.context.event.BeanCreatedEvent
import io.micronaut.context.event.BeanCreatedEventListener
import io.micronaut.core.annotation.Introspected
import javax.inject.Singleton

@Singleton
@Introspected
internal class ObjectMapperBeanEventListener : BeanCreatedEventListener<ObjectMapper> {

    override fun onCreated(event: BeanCreatedEvent<ObjectMapper>): ObjectMapper {
        return event.bean.apply {
            this.propertyNamingStrategy = PropertyNamingStrategy.SNAKE_CASE
            this.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            this.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            this.registerModule(JavaTimeModule()) // for JSR310
            this.registerModule(
                KotlinModule(
                    nullToEmptyCollection = true,
                    nullToEmptyMap = true,
                    nullIsSameAsDefault = true
                )
            )
        }
    }
}
