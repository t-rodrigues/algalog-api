package dev.trodrigues.algalogapi.api.controllers

import dev.trodrigues.algalogapi.api.requests.ClientRequest
import dev.trodrigues.algalogapi.api.requests.toEntity
import dev.trodrigues.algalogapi.domain.entities.Client
import dev.trodrigues.algalogapi.domain.services.ClientCatalogService
import org.springframework.data.domain.Page
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
    fun list(pageable: Pageable): Page<Client> = clientCatalogService.findAll(pageable)

    @GetMapping("/{clientId}")
    fun findByClientId(@PathVariable clientId: UUID): Client =
        clientCatalogService.findById(clientId)

    @PostMapping
    fun createClient(@RequestBody @Valid clientRequest: ClientRequest): ResponseEntity<Client> {
        val client = clientCatalogService.createClient(clientRequest.toEntity())
        val uri = URI("/clients/${client.id}")
        return ResponseEntity.created(uri).body(client)
    }

    @PutMapping("/{clientId}")
    fun updateClient(
        @PathVariable clientId: UUID,
        @RequestBody @Valid clientRequest: ClientRequest
    ): Client =
        clientCatalogService.updateClient(clientId, clientRequest)

    @DeleteMapping("/{clientId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteClient(@PathVariable clientId: UUID) =
        clientCatalogService.deleteById(clientId)

}
