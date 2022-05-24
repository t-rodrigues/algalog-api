package dev.trodrigues.algalogapi.api.controllers

import dev.trodrigues.algalogapi.api.requests.DeliveryRequest
import dev.trodrigues.algalogapi.api.responses.DeliveryResponse
import dev.trodrigues.algalogapi.api.responses.PageResponse
import dev.trodrigues.algalogapi.api.responses.mapper.toPageResponse
import dev.trodrigues.algalogapi.api.responses.mapper.toResponse
import dev.trodrigues.algalogapi.domain.services.CompleteDeliveryService
import dev.trodrigues.algalogapi.domain.services.DeliveryRequestService
import dev.trodrigues.algalogapi.domain.services.exceptions.NotFoundException
import dev.trodrigues.algalogapi.infra.repositories.DeliveryRepository
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/delivery")
class DeliveryController(
    private val deliveryRequestService: DeliveryRequestService,
    private val completeDeliveryService: CompleteDeliveryService,
    private val deliveryRepository: DeliveryRepository
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun requestDelivery(@RequestBody @Valid deliveryRequest: DeliveryRequest): DeliveryResponse {
        val delivery = deliveryRequestService.request(deliveryRequest)
        return delivery.toResponse()
    }

    @GetMapping
    fun list(pageable: Pageable): PageResponse<DeliveryResponse> {
        val deliveries = deliveryRepository.findAll(pageable)
        return deliveries.map { it.toResponse() }.toPageResponse()
    }

    @GetMapping("/{deliveryId}")
    fun findByDeliveryId(@PathVariable deliveryId: UUID): DeliveryResponse {
        val delivery =
            deliveryRepository.findById(deliveryId).orElseThrow { NotFoundException("delivery not found: $deliveryId") }
        return delivery.toResponse()
    }

    @PutMapping("/{deliveryId}/finish")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun finishDelivery(@PathVariable deliveryId: UUID) =
        completeDeliveryService.finish(deliveryId)

}
