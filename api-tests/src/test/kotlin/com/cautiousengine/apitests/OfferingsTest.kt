package com.cautiousengine.apitests

import io.restassured.RestAssured.given
import io.restassured.module.kotlin.extensions.When
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.util.UUID

class OfferingsTest {

    companion object {
        private const val ROUTE = "http://localhost:8080/offerings"
    }

    private val offeringJson = """
        {
          "name": "Ethiopia Kochere & Yirgacheffe Jabanto",
          "tasting_notes": "Blueberry, Chocolate, Honey",
          "description": "The Jabanto group formed in 2017, after changes in the Ethiopian coffee policy permitted smallholder farmers to be able to directly export and sell their coffees. The group has worked hard to build an enterprising business from scratch and their commitment to careful harvesting and processing each year results in some of our favorite offerings. This year’s production of Jabanto Natural Sundried tastes like blueberry, chocolate, and honey.",
          "url": "https://counterculturecoffee.com/shop/coffee/jabanto-natural-sundried/${UUID.randomUUID().toString()}",
          "roaster_name": "Counter Culture Coffee"
        }
    """.trimIndent()

    @Nested
    inner class Index {

        @Test
        fun `get offerings returns 200 OK`() {
            given()
                .When { get(ROUTE) }
                .then()
                .statusCode(200)
        }
    }

    @Nested
    inner class Create {

        @Test
        fun `create offerings returns 201 Created`() {
            given().body(offeringJson)
                .When { post(ROUTE) }
                .then()
                .statusCode(201)
        }
    }

}