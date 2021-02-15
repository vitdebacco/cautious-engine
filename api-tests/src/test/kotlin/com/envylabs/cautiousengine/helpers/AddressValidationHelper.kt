package com.envylabs.cautiousengine.helpers

import io.restassured.response.ValidatableResponse
import org.hamcrest.CoreMatchers
import org.hamcrest.Matchers

object AddressValidationHelper {

    fun validateAddressBody(vr: ValidatableResponse) {
        vr.body("street", CoreMatchers.isA(String::class.java))
        vr.body("street", Matchers.notNullValue())

        vr.body("city", CoreMatchers.isA(String::class.java))
        vr.body("city", Matchers.notNullValue())

        vr.body("state", CoreMatchers.isA(String::class.java))
        vr.body("state", Matchers.notNullValue())

        vr.body("zipCode", CoreMatchers.isA(String::class.java))
        vr.body("zipCode", Matchers.notNullValue())

        vr.body("createdAt", CoreMatchers.isA(String::class.java))
        vr.body("createdAt", Matchers.notNullValue())
    }

    fun validateAddressesBodyArrayElement(vr: ValidatableResponse, element: Int = 0) {
        vr.body("addresses[$element].street", CoreMatchers.isA(String::class.java))
        vr.body("addresses[$element].street", Matchers.notNullValue())

        vr.body("addresses[$element].city", CoreMatchers.isA(String::class.java))
        vr.body("addresses[$element].city", Matchers.notNullValue())

        vr.body("addresses[$element].state", CoreMatchers.isA(String::class.java))
        vr.body("addresses[$element].state", Matchers.notNullValue())

        vr.body("addresses[$element].zipCode", CoreMatchers.isA(String::class.java))
        vr.body("addresses[$element].zipCode", Matchers.notNullValue())

        vr.body("addresses[$element].createdAt", CoreMatchers.isA(String::class.java))
        vr.body("addresses[$element].createdAt", Matchers.notNullValue())
    }
}
