package dev.trodrigues.algalogapi.api.controllers

import dev.trodrigues.algalogapi.api.requests.DeliveryRequest
import dev.trodrigues.algalogapi.domain.entities.Delivery
import dev.trodrigues.algalogapi.domain.services.DeliveryRequestService
import dev.trodrigues.algalogapi.domain.services.exceptions.NotFoundException
import dev.trodrigues.algalogapi.infra.repositories.DeliveryRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/delivery")
class DeliveryController(
    private val deliveryRequestService: DeliveryRequestService, private val deliveryRepository: DeliveryRepository
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun requestDelivery(@RequestBody @Valid deliveryRequest: DeliveryRequest): Delivery =
        deliveryRequestService.request(deliveryRequest)

    @GetMapping
    fun list(pageable: Pageable): Page<Delivery> = deliveryRepository.findAll(pageable)

    @GetMapping("/{deliveryId}")
    fun findByDeliveryId(@PathVariable deliveryId: UUID): Delivery =
        deliveryRepository.findById(deliveryId).orElseThrow { NotFoundException("delivery not found: $deliveryId") }

}
