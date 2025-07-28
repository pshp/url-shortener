package io.github.pshp.shortener.service

import io.github.pshp.shortener.Util.sha256Base64Url
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

        // generate a Base64 Sha256 hash and take the first N characters
        val fullHash = originalUrl.sha256Base64Url()
        val shortCode = fullHash.take(shortCodeLength)

        val newRecord = UrlMappingModel(originalUrl = originalUrl, fullHash = fullHash, shortCode = shortCode)

        return try {
            repository.save(newRecord)
        } catch (e: DataIntegrityViolationException) {
            // on unique key violation fetch existing data
            repository.findByOriginalUrl(originalUrl) ?: throw e
        }
    }

    // Looks up a shortcode and returns its URL mapping or throws 404 if not found
    fun findByShortCode(shortCode: String): UrlMappingModel {
        return repository.findByShortCode(shortCode)
    }
}