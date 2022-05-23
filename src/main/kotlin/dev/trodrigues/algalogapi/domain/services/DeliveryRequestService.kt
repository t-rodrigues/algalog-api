package dev.trodrigues.algalogapi.domain.services

import dev.trodrigues.algalogapi.api.requests.DeliveryRequest
import dev.trodrigues.algalogapi.domain.entities.Delivery

interface DeliveryRequestService {

    fun request(deliveryRequest: DeliveryRequest): Delivery

}
