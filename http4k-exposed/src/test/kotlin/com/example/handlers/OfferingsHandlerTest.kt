package com.example.handlers

import com.example.services.OfferingsService
import io.kotest.core.spec.style.DescribeSpec
import io.mockk.every
import io.mockk.mockk
import org.http4k.core.MemoryResponse
import org.http4k.core.Response
import org.http4k.core.Status

class OfferingsHandlerTest: DescribeSpec({

    val service = mockk<OfferingsService>() {
        every { getAll() } returns listOf()
    }

    val handler = OfferingsHandler(service)

    describe("index") {
        context("no offerings") {
            it("returns 200 and empty response body") {
                val response = handler.index()
            }

        }

    }
})