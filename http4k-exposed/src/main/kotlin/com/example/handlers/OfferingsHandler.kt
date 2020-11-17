package com.example.handlers

import com.example.com.example.models.api.OfferingCreateRequest
import com.example.com.example.models.api.OfferingUpdateRequest
import com.example.models.data.Offering
import com.example.services.OfferingsServiceImpl
import org.http4k.core.Body
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.core.with
import org.http4k.format.Jackson.auto
import org.http4k.lens.Path
import org.http4k.lens.nonEmptyString

class OfferingsHandler {

    private val offeringsService = OfferingsServiceImpl()

    fun index(): Response {
        val offeringsLens = Body.auto<Iterable<Offering>>().toLens()
        val offerings = offeringsService.getAll()

        return Response(Status.OK).with(offeringsLens of offerings)
    }

    private val offeringIdLens = Path.nonEmptyString().of("offering_id")

    fun show() = { request: Request ->
        val offeringId = offeringIdLens(request)

        val offering = offeringsService.getById(offeringId) ?: throw Exception("Not Found")

        val offeringLens = Body.auto<Offering>().toLens()
        offeringLens(offering, Response(Status.OK))
    }

    fun create() = { request: Request ->
        val offeringCreateLens = Body.auto<OfferingCreateRequest>().toLens()
        val offeringLens = Body.auto<Offering>().toLens()

        val result = offeringsService.create(offeringCreateLens(request))
        offeringLens(result, Response(Status.OK))
    }

    fun update() = { request: Request ->
        val offeringUpdateLens = Body.auto<OfferingUpdateRequest>().toLens()
        val offeringId = offeringIdLens(request)

        val updateResult = offeringsService.update(offeringId, offeringUpdateLens(request))

        val offeringLens = Body.auto<Offering>().toLens()
        offeringLens(updateResult, Response(Status.OK))
    }

    fun delete() = { request: Request ->
        val offeringId = offeringIdLens(request)

        val deleteResult = offeringsService.delete(offeringId)
        val offeringLens = Body.auto<Offering>().toLens()
        offeringLens(deleteResult, Response(Status.OK))
    }
}