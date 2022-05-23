package dev.trodrigues.algalogapi.api.controllers

import dev.trodrigues.algalogapi.api.requests.OccurrenceRequest
import dev.trodrigues.algalogapi.api.responses.OccurrenceResponse
import dev.trodrigues.algalogapi.api.responses.mapper.toResponse
import dev.trodrigues.algalogapi.domain.services.FindDeliveryService
import dev.trodrigues.algalogapi.domain.services.OccurrenceRecordService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/delivery/{deliveryId}/occurrences")
class OccurrenceController(
    private val occurrenceRecordService: OccurrenceRecordService,
    private val findDeliveryService: FindDeliveryService
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun record(
        @PathVariable deliveryId: UUID,
        @RequestBody @Valid occurrenceRequest: OccurrenceRequest
    ): OccurrenceResponse {
        val occurrence = occurrenceRecordService.record(deliveryId, occurrenceRequest.description)
        return occurrence.toResponse()
    }

    @GetMapping
    fun list(@PathVariable deliveryId: UUID): List<OccurrenceResponse> {
        val delivery = findDeliveryService.findDeliveryById(deliveryId)
        return delivery.occurrences.map { it.toResponse() }
    }

}
