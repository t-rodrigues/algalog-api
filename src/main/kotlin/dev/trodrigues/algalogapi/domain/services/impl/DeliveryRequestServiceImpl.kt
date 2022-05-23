package dev.trodrigues.algalogapi.domain.services.impl

import dev.trodrigues.algalogapi.api.requests.DeliveryRequest
import dev.trodrigues.algalogapi.api.requests.toEntity
import dev.trodrigues.algalogapi.domain.entities.Delivery
import dev.trodrigues.algalogapi.domain.entities.DeliveryStatus
import dev.trodrigues.algalogapi.domain.services.DeliveryRequestService
import dev.trodrigues.algalogapi.domain.services.exceptions.BusinessException
import dev.trodrigues.algalogapi.infra.repositories.ClientRepository
import dev.trodrigues.algalogapi.infra.repositories.DeliveryRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class DeliveryRequestServiceImpl(
    private val deliveryRepository: DeliveryRepository,
    private val clientRepository: ClientRepository
) : DeliveryRequestService {
    override fun request(deliveryRequest: DeliveryRequest): Delivery {
        val client =
            clientRepository.findById(deliveryRequest.client.id!!)
                .orElseThrow { BusinessException("Client not found: ${deliveryRequest.client.id}") }
        val delivery = deliveryRequest.toEntity(client)
        delivery.status = DeliveryStatus.PENDING
        delivery.orderDate = LocalDateTime.now()
        return deliveryRepository.save(delivery)
    }
}
