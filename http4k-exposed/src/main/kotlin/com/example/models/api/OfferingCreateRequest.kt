package com.example.com.example.models.api

import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming
import java.net.URL

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
data class OfferingCreateRequest (
    val name: String,
    val tastingNotes: String,
    val description: String,
    val url: URL,
    val roasterName: String
)