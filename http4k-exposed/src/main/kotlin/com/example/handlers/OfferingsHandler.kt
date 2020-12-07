package com.example.handlers

import com.example.exceptions.NotFoundException
import com.example.models.api.OfferingCreateRequest
import com.example.models.api.OfferingUpdateRequest
import com.example.models.data.Offering
import com.example.services.OfferingsService
import org.http4k.core.Body
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.core.with
import org.http4k.format.Jackson.auto
import org.http4k.lens.Path
import org.http4k.lens.nonEmptyString

class OfferingsHandler(private val offeringsService: OfferingsService) {

    private val offeringIdLens = Path.nonEmptyString().map(String::toLong).of("offering_id")

    fun index() = { _: Request ->
        val offeringsLens = Body.auto<Iterable<Offering>>().toLens()
        val offerings = offeringsService.getAll()

        Response(Status.OK).with(offeringsLens of offerings)
    }

    fun show() = { request: Request ->
        val offeringId = offeringIdLens(request)

        val offering = offeringsService.getById(offeringId)
            ?: throw NotFoundException("offering not found for id: $offeringId")

        val offeringLens = Body.auto<Offering>().toLens()
        offeringLens(offering, Response(Status.OK))
    }

    fun create() = { request: Request ->
        val offeringCreateLens = Body.auto<OfferingCreateRequest>().toLens()
        val offeringLens = Body.auto<Offering>().toLens()

        val result = offeringsService.create(offeringCreateLens(request))
        offeringLens(result, Response(Status.CREATED))
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