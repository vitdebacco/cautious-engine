package com.example.services

import com.example.com.example.models.api.OfferingCreateRequest
import com.example.com.example.models.api.OfferingUpdateRequest
import com.example.models.data.Offering
import com.example.models.data.OfferingEntity
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.Instant
import java.util.UUID

interface OfferingsService {
    fun getAll(): Iterable<Offering>
    fun getById(id: String): Offering?
    fun create(offeringCreateRequest: OfferingCreateRequest): Offering
    fun update(id: String, offeringUpdateRequest: OfferingUpdateRequest): Offering
    fun delete(id: String): Offering
}

class OfferingsServiceImpl: OfferingsService {

    override fun getAll(): Iterable<Offering> = transaction {
        OfferingEntity.all().map(OfferingEntity::toOffering)
    }

    override fun getById(id: String): Offering? = transaction {
        OfferingEntity.findById(UUID.fromString(id))?.toOffering()
    }

    override fun create(offeringCreateRequest: OfferingCreateRequest): Offering = transaction {
        OfferingEntity.new {
            name = offeringCreateRequest.name
            description = offeringCreateRequest.description
            tastingNotes = offeringCreateRequest.tastingNotes
            roasterName = offeringCreateRequest.roasterName
            url = offeringCreateRequest.url.toString()
            createdAt = Instant.now()
        }.toOffering()
    }

    // TODO: change Offering to OfferingCreateRequest
    override fun update(id: String, offeringUpdateRequest: OfferingUpdateRequest): Offering = transaction {
        val now = Instant.now()
        val uuid = UUID.fromString(id)

        OfferingEntity[uuid].apply {
            description = offeringUpdateRequest.description
            updatedAt = now
        }.toOffering()
    }

    override fun delete(id: String): Offering = transaction {
        val uuid = UUID.fromString(id)

        val result = OfferingEntity[uuid].toOffering().copy(deletedAt = Instant.now())
        OfferingEntity[uuid].delete()

        result
    }
}