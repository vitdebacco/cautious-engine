package com.example.models.data

import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.`java-time`.timestamp
import java.time.Instant

object Ratings : LongIdTable() {
    val value = integer("value")
    val comment = text("comment")
    val username = varchar("username", 255)
    val offering = reference("offering", Offerings)
    val createdAt = timestamp("created_at")
    val updatedAt = timestamp("updated_at").nullable()
    val deletedAt = timestamp("deleted_at").nullable()
}

class RatingEntity(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<RatingEntity>(Ratings)

    var value by Ratings.value
    var comment by Ratings.comment
    var username by Ratings.username
    var offering by OfferingEntity referencedOn Ratings.offering
    var createdAt by Ratings.createdAt
    var updatedAt by Ratings.updatedAt
    var deletedAt by Ratings.deletedAt

    fun toRating(): Rating {
        return Rating(
            id = id.value,
            value = value,
            comment = comment,
            username = username,
            offering = offering.toOffering(),
            createdAt = createdAt,
            updatedAt = updatedAt,
            deletedAt = deletedAt
        )
    }
}

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
data class Rating(
    val id: Long,
    val value: Int,
    val comment: String,
    val username: String,
    val offering: Offering,
    val createdAt: Instant = Instant.now(),
    val updatedAt: Instant? = null,
    val deletedAt: Instant? = null
)