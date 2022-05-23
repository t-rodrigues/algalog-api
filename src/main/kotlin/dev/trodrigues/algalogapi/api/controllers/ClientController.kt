package dev.trodrigues.algalogapi.api.controllers

import dev.trodrigues.algalogapi.api.requests.ClientRequest
import dev.trodrigues.algalogapi.api.requests.toEntity
import dev.trodrigues.algalogapi.api.responses.ClientResponse
import dev.trodrigues.algalogapi.api.responses.PageResponse
import dev.trodrigues.algalogapi.api.responses.mapper.toPageResponse
import dev.trodrigues.algalogapi.api.responses.mapper.toResponse
import dev.trodrigues.algalogapi.domain.services.ClientCatalogService
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/clients")
class ClientController(
    private val clientCatalogService: ClientCatalogService
) {

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE])
    fun list(pageable: Pageable): PageResponse<ClientResponse> =
        clientCatalogService.findAll(pageable).map { it.toResponse() }.toPageResponse()

    @GetMapping("/{clientId}")
    fun findByClientId(@PathVariable clientId: UUID): ClientResponse =
        clientCatalogService.findById(clientId).toResponse()

    @PostMapping
    fun createClient(@RequestBody @Valid clientRequest: ClientRequest): ResponseEntity<ClientResponse> {
        val client = clientCatalogService.createClient(clientRequest.toEntity())
        val uri = URI("/clients/${client.id}")
        return ResponseEntity.created(uri).body(client.toResponse())
    }

    @PutMapping("/{clientId}")
    fun updateClient(
        @PathVariable clientId: UUID, @RequestBody @Valid clientRequest: ClientRequest
    ): ClientResponse =
        clientCatalogService.updateClient(clientId, clientRequest).toResponse()

    @DeleteMapping("/{clientId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteClient(@PathVariable clientId: UUID) =
        clientCatalogService.deleteById(clientId)

}
