package dev.trodrigues.algalogapi.api.responses.mapper

import dev.trodrigues.algalogapi.api.responses.OccurrenceResponse
import dev.trodrigues.algalogapi.domain.entities.Occurrence

fun Occurrence.toResponse(): OccurrenceResponse = OccurrenceResponse(
    id = this.id!!,
    description = this.description,
    occurrenceDate = this.occurrenceDate
)
