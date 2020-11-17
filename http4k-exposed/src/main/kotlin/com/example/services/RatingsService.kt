package com.example.services

import com.example.models.api.RatingRequest
import com.example.models.data.OfferingEntity
import com.example.models.data.Rating
import com.example.models.data.RatingEntity
import com.example.models.data.Ratings
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.Instant
import java.util.UUID

interface RatingsService {
    fun findAll(): Iterable<Rating>
    fun findById(id: Long): Rating
    fun create(ratingRequest: RatingRequest): Rating
    fun update(id: Long, ratingRequest: RatingRequest): Rating
    fun delete(id: Long): Rating
}

class RatingsServiceImpl: RatingsService {
    override fun findAll(): Iterable<Rating> = transaction {
        RatingEntity.all().map(RatingEntity::toRating)
    }

    fun findAllByOfferingId(offeringId: String): Iterable<Rating> = transaction {
        val offeringUUID = UUID.fromString(offeringId)

        RatingEntity.find { Ratings.offering eq offeringUUID }.map(RatingEntity::toRating)
    }

    override fun findById(id: Long): Rating = transaction {
        RatingEntity[id].toRating()
    }

    override fun create(ratingRequest: RatingRequest): Rating = transaction {
        val offering = getOffering(ratingRequest.offeringId)

        RatingEntity.new {
            this.value = ratingRequest.value
            this.comment = ratingRequest.comment
            this.username = ratingRequest.username
            this.offering = offering
            this.createdAt = Instant.now()
        }.toRating()
    }

    override fun update(id: Long, ratingRequest: RatingRequest): Rating = transaction {
        val now = Instant.now()

        RatingEntity[id].apply {
            this.value = ratingRequest.value
            this.comment = ratingRequest.comment
            this.username = ratingRequest.username
            this.updatedAt = now
        }.toRating()
    }

    override fun delete(id: Long): Rating = transaction {
        val result = RatingEntity[id].toRating().copy(deletedAt = Instant.now())
        RatingEntity[id].delete()

        result
    }

    // This should leveraged [OfferingsService] for this
    private fun getOffering(offeringId: String): OfferingEntity {
        val offeringUuid = UUID.fromString(offeringId)
        return OfferingEntity[offeringUuid]
    }
}