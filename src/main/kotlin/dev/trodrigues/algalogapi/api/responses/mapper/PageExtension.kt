package dev.trodrigues.algalogapi.api.responses.mapper

import dev.trodrigues.algalogapi.api.responses.PageResponse
import org.springframework.data.domain.Page

fun <T> Page<T>.toPageResponse(): PageResponse<T> = PageResponse(
    items = this.content,
    currentPage = this.number,
    totalPages = this.totalPages,
    totalItems = this.totalElements
)
