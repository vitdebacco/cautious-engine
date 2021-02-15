package com.envylabs.cautiousengine.api

import com.envylabs.cautiousengine.helpers.TestConstants
import io.restassured.http.ContentType
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import org.hamcrest.CoreMatchers
import org.hamcrest.Matchers
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class AddressesTest {

    @Nested
    inner class Index {

        @Test
        fun `returns 200 OK and expected payload`() {
            Given {
                contentType(ContentType.JSON)
                accept(ContentType.JSON)
            } When {
                get("${TestConstants.BASE_URL}/addresses")
            } Then {
                statusCode(200)
                body("addresses", CoreMatchers.isA(List::class.java))
                body("addresses", Matchers.notNullValue())
                body("addresses.size()", Matchers.greaterThanOrEqualTo(0))
                body("addresses.size()", Matchers.lessThanOrEqualTo(100))
                body("count", CoreMatchers.isA(Int::class.java))
                body("count", Matchers.notNullValue())
            }
        }
    }

    @Nested
    inner class Show {

        @Test
        fun `returns 200 OK and expected payload`() {
            Given {
                contentType(ContentType.JSON)
                accept(ContentType.JSON)
            } When {
                get("${TestConstants.BASE_URL}/addresses/a1b2c3")
            } Then {
                statusCode(200)
                body("street", CoreMatchers.isA(String::class.java))
                body("street", Matchers.notNullValue())

                body("city", CoreMatchers.isA(String::class.java))
                body("city", Matchers.notNullValue())

                body("state", CoreMatchers.isA(String::class.java))
                body("state", Matchers.notNullValue())

                body("zipCode", CoreMatchers.isA(String::class.java))
                body("zipCode", Matchers.notNullValue())

                body("createdAt", CoreMatchers.isA(String::class.java))
                body("createdAt", Matchers.notNullValue())
            }
        }
    }
}
