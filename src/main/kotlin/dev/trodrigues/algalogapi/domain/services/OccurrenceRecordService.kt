package dev.trodrigues.algalogapi.domain.services

import dev.trodrigues.algalogapi.domain.entities.Occurrence
import java.util.*

interface OccurrenceRecordService {

    fun record(deliveryId: UUID, description: String): Occurrence

}
