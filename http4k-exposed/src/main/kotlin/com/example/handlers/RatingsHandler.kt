package com.example.handlers

import com.example.models.api.RatingRequest
import com.example.models.data.Rating
import com.example.services.RatingsServiceImpl
import org.http4k.core.Body
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.core.with
import org.http4k.format.Jackson.auto
import org.http4k.lens.Path
import org.http4k.lens.nonEmptyString

class RatingsHandler {

    private val ratingsService = RatingsServiceImpl()

    fun index() = { _: Request ->
        val ratingsLens = Body.auto<Iterable<Rating>>().toLens()
        val result = ratingsService.findAll()

        Response(Status.OK).with(ratingsLens of result)
    }

    private val ratingIdLens = Path.nonEmptyString().map(String::toLong).of("rating_id")
    private val offeringIdLens = Path.nonEmptyString().of("offering_id")

    fun indexByOffering() = { request: Request ->
        val offeringId = offeringIdLens(request)
        val ratingsLens = Body.auto<Iterable<Rating>>().toLens()
        val result = ratingsService.findAllByOfferingId(offeringId)

        Response(Status.OK).with(ratingsLens of result)
    }

    fun show() = { request: Request ->
        val ratingId = ratingIdLens(request)

        val rating = ratingsService.findById(ratingId)
        val ratingLens = Body.auto<Rating>().toLens()

        ratingLens(rating, Response(Status.OK))
    }

    fun create() = { request: Request ->
        val offeringId = offeringIdLens(request)
        val ratingRequestLens = Body.auto<RatingRequest>().toLens()
        val ratingLens = Body.auto<Rating>().toLens()

        val result = ratingsService.create(ratingRequestLens(request))

        ratingLens(result, Response(Status.OK))
    }

    fun update() = { request: Request ->
        val ratingRequestLens = Body.auto<RatingRequest>().toLens()
        val ratingId = ratingIdLens(request)

        val updateResult = ratingsService.update(ratingId, ratingRequestLens(request))

        val ratingLens = Body.auto<Rating>().toLens()

        ratingLens(updateResult, Response(Status.OK))
    }

    fun delete() = { request: Request ->
        val ratingId = ratingIdLens(request)

        val deleteResult = ratingsService.delete(ratingId)
        val ratingLens = Body.auto<Rating>().toLens()

        ratingLens(deleteResult, Response(Status.OK))
    }
}