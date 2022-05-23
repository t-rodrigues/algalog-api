package dev.trodrigues.algalogapi.domain.services.impl

import dev.trodrigues.algalogapi.domain.entities.Delivery
import dev.trodrigues.algalogapi.domain.services.FindDeliveryService
import dev.trodrigues.algalogapi.domain.services.exceptions.NotFoundException
import dev.trodrigues.algalogapi.infra.repositories.DeliveryRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class FindDeliveryServiceImpl(
    private val deliveryRepository: DeliveryRepository
) : FindDeliveryService {
    override fun findDeliveryById(deliveryId: UUID): Delivery =
        deliveryRepository.findById(deliveryId).orElseThrow { NotFoundException("delivery not found: $deliveryId") }
}
