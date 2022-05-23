package dev.trodrigues.algalogapi.api.controllers

import dev.trodrigues.algalogapi.api.requests.DeliveryRequest
import dev.trodrigues.algalogapi.domain.entities.Delivery
import dev.trodrigues.algalogapi.domain.services.DeliveryRequestService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/delivery")
class DeliveryController(
    private val deliveryRequestService: DeliveryRequestService
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun requestDelivery(@RequestBody @Valid deliveryRequest: DeliveryRequest): Delivery =
        deliveryRequestService.request(deliveryRequest)

}
