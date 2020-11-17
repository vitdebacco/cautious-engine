package com.example.models.api

import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
data class RatingRequest(
    val value: Int,
    val comment: String,
    val offeringId: String,
    val username: String
)