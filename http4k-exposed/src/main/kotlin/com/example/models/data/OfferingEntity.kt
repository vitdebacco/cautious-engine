package com.example.models.data

import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.`java-time`.timestamp
import java.net.URL
import java.time.Instant
import java.util.UUID

object Offerings : UUIDTable() {
    val name = varchar("name", 50)
    val tastingNotes = varchar("tasting_notes", 50)
    val description = text("description")
    val url = varchar("url", 255).uniqueIndex()
    val roasterName = varchar("roasterName", 255)
    val createdAt = timestamp("created_at")
    val updatedAt = timestamp("updated_at").nullable()
    val deletedAt = timestamp("deleted_at").nullable()
}

class OfferingEntity(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<OfferingEntity>(Offerings)

    var name by Offerings.name
    var tastingNotes by Offerings.tastingNotes
    var description by Offerings.description
    var url by Offerings.url
    var roasterName by Offerings.roasterName
    var createdAt by Offerings.createdAt
    var updatedAt by Offerings.updatedAt
    var deletedAt by Offerings.deletedAt

    fun toOffering(): Offering {
        return Offering(
            id = id.value,
            name = name,
            tastingNotes = tastingNotes,
            description = description,
            url = URL(url),
            roasterName = roasterName,
            createdAt = createdAt,
            updatedAt = updatedAt,
            deletedAt = deletedAt
        )
    }
}

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
data class Offering(
    val id: UUID,
    val name: String,
    val tastingNotes: String,
    val description: String,
    val url: URL,
    val roasterName: String,
    val createdAt: Instant = Instant.now(),
    val updatedAt: Instant? = null,
    val deletedAt: Instant? = null
)