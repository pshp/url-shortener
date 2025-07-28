package io.github.pshp.shortener.controller

import io.github.pshp.shortener.dto.EncodeUrlRequest
import io.github.pshp.shortener.model.UrlMappingModel
import io.github.pshp.shortener.service.UrlMappingService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/url-mappings")
class UrlMappingController (
    private val service: UrlMappingService
) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun shorten(@Valid @RequestBody req: EncodeUrlRequest): UrlMappingModel {
        return service.shortenUrl(req.url)
        // TODO: if record already exists return 200 OK instead of 201 Created
    }

    @GetMapping("/{code}")
    fun resolve(@PathVariable code: String): UrlMappingModel {
        return service.resolveShortCode(code)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "short code not found: $code")
    }

    // TODO: add central error handing service, e.g. https://codesignal.com/learn/courses/advanced-restful-techniques/lessons/error-handling-in-spring-boot-with-kotlin
}