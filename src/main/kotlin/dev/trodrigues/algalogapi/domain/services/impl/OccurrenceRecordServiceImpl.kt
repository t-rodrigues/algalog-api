package dev.trodrigues.algalogapi.domain.services.impl

import dev.trodrigues.algalogapi.domain.entities.Occurrence
import dev.trodrigues.algalogapi.domain.services.FindDeliveryService
import dev.trodrigues.algalogapi.domain.services.OccurrenceRecordService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class OccurrenceRecordServiceImpl(
    private val findDeliveryService: FindDeliveryService
) : OccurrenceRecordService {
    @Transactional
    override fun record(deliveryId: UUID, description: String): Occurrence {
        val delivery = findDeliveryService.findDeliveryById(deliveryId)
        return delivery.addOccurrence(description)
    }
}
