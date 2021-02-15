package com.envylabs.cautiousengine.api

import com.envylabs.cautiousengine.helpers.AddressValidationHelper
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
                body("addresses.size()", Matchers.greaterThanOrEqualTo(1))
                body("addresses.size()", Matchers.lessThanOrEqualTo(100))
                body("count", CoreMatchers.isA(Int::class.java))
                body("count", Matchers.notNullValue())

                AddressValidationHelper.validateAddressesBodyArrayElement(this)
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
                AddressValidationHelper.validateAddressBody(this)
            }
        }
    }
}
