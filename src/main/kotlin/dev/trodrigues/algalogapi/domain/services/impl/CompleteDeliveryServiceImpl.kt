package dev.trodrigues.algalogapi.domain.services.impl

import dev.trodrigues.algalogapi.domain.services.CompleteDeliveryService
import dev.trodrigues.algalogapi.domain.services.FindDeliveryService
import dev.trodrigues.algalogapi.infra.repositories.DeliveryRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class CompleteDeliveryServiceImpl(
    private val findDeliveryService: FindDeliveryService,
    private val deliveryRepository: DeliveryRepository
) : CompleteDeliveryService {
    @Transactional
    override fun finish(deliveryId: UUID) {
        val delivery = findDeliveryService.findDeliveryById(deliveryId)
        delivery.finish()
        deliveryRepository.save(delivery)
    }
}
