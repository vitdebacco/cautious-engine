package com.example.services

import com.example.exceptions.ConflictException
import com.example.exceptions.UnknownException
import com.example.models.api.OfferingCreateRequest
import com.example.models.api.OfferingUpdateRequest
import com.example.models.data.Offering
import com.example.models.data.OfferingEntity
import org.jetbrains.exposed.exceptions.ExposedSQLException
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.Instant

interface OfferingsService {
    fun getAll(): Iterable<Offering>
    fun getById(id: Long): Offering?
    fun create(offeringCreateRequest: OfferingCreateRequest): Offering
    fun update(id: Long, offeringUpdateRequest: OfferingUpdateRequest): Offering
    fun delete(id: Long): Offering
}

class OfferingsServiceImpl : OfferingsService {

    override fun getAll(): Iterable<Offering> = transaction {
        OfferingEntity.all().map(OfferingEntity::toOffering)
    }

    override fun getById(id: Long): Offering? = transaction {
        OfferingEntity.findById(id)?.toOffering()
    }

    override fun create(offeringCreateRequest: OfferingCreateRequest): Offering = transaction {
        try {
            OfferingEntity.new {
                name = offeringCreateRequest.name
                description = offeringCreateRequest.description
                tastingNotes = offeringCreateRequest.tastingNotes
                roasterName = offeringCreateRequest.roasterName
                url = offeringCreateRequest.url.toString()
                createdAt = Instant.now()
            }.toOffering()
        } catch (e: ExposedSQLException) {
            // For more Postgres error codes, see: https://www.postgresql.org/docs/10/errcodes-appendix.html
            when (e.sqlState.toInt()) {
                23505 -> throw ConflictException(message = e.message ?: "", e)
                else -> throw UnknownException(message = e.message ?: "An unknown exception occurred", e)
            }
        }
    }

    // TODO: change Offering to OfferingCreateRequest
    override fun update(id: Long, offeringUpdateRequest: OfferingUpdateRequest): Offering = transaction {
        val now = Instant.now()

        OfferingEntity[id].apply {
            description = offeringUpdateRequest.description
            updatedAt = now
        }.toOffering()
    }

    override fun delete(id: Long): Offering = transaction {
        val result = OfferingEntity[id].toOffering().copy(deletedAt = Instant.now())
        OfferingEntity[id].delete()

        result
    }
}