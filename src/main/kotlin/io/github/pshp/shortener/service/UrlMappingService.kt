package io.github.pshp.shortener.service

import io.github.pshp.shortener.model.UrlMappingModel
import io.github.pshp.shortener.repository.UrlMappingRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.util.UUID

@Service
class UrlMappingService(
    private val repository: UrlMappingRepository
) {
    fun shortenUrl(originalUrl: String): UrlMappingModel {
        // TODO: add lookup if url has already been encoded and return existing record if found

        val encodedUrl = generateUuid(originalUrl)
        val newRecord = UrlMappingModel(originalUrl = originalUrl, encodedUrl = encodedUrl)
        return repository.save(newRecord)
    }

    fun getByEncodedUrl(encodedUrl: String): UrlMappingModel {
        return repository.findByEncodedUrl(encodedUrl) ?:
        throw ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "Record not found for encoded url: $encodedUrl"
        )
    }

    private fun generateUuid(originalUrl:String):String {
        val newUuid = UUID.randomUUID().toString()
        return newUuid
    }
}