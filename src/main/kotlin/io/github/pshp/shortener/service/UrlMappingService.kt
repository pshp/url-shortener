package io.github.pshp.shortener.service

import io.github.pshp.shortener.util.hashUrl
import io.github.pshp.shortener.model.UrlMappingModel
import io.github.pshp.shortener.repository.UrlMappingRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Service

// service for creating and retrieving URL mappings
@Service
class UrlMappingService(
    private val repository: UrlMappingRepository,

    // configurable length for generated short codes
    @Value("\${app.short-code-length:8}")
    private val shortCodeLength: Int
) {

    // creates or returns an existing shortcode mapping for a given URL
    fun shortenUrl(originalUrl: String): UrlMappingModel {

        // generate a Base64URL with Sha256 hash and take the first N characters
        val fullHash = originalUrl.hashUrl()
        val shortCode = fullHash.take(shortCodeLength)

        val newRecord = UrlMappingModel(originalUrl = originalUrl, fullHash = fullHash, shortCode = shortCode)

        return try {
            repository.save(newRecord)
        } catch (e: DataIntegrityViolationException) {
            // on unique key violation fetch existing data
            // identical URLs will create identical hashes, so this avoids creating unesesary records
            repository.findByOriginalUrl(originalUrl) ?: throw e
        }
    }

    // Looks up a shortcode and returns its URL mapping. Returns null if not found
    fun resolveShortCode(shortCode: String): UrlMappingModel? {
        return repository.findByShortCode(shortCode)
    }
}