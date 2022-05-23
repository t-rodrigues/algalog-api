package dev.trodrigues.algalogapi.api.responses

import java.time.LocalDateTime
import java.util.*

data class OccurrenceResponse(
    val id: UUID,
    val description: String,
    val occurrenceDate: LocalDateTime? = LocalDateTime.now()
)
