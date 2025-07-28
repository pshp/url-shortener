package io.github.pshp.shortener.service

import io.github.pshp.shortener.Util.sha256Base64Url
import io.github.pshp.shortener.model.UrlMappingModel
import io.github.pshp.shortener.repository.UrlMappingRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service


@Service
class UrlMappingService(
    private val repository: UrlMappingRepository,
    @Value("\${app.short-code-length:8}")
    private val shortCodeLength: Int
) {
    fun shortenUrl(originalUrl: String): UrlMappingModel {
        val existing = repository.findByOriginalUrl(originalUrl)
        if (existing != null) {
            return existing
        }

        val fullHash = originalUrl.sha256Base64Url()
        val shortCode = fullHash.take(shortCodeLength)

        val newRecord = UrlMappingModel(originalUrl = originalUrl, fullHash = fullHash, shortCode = shortCode)
        return repository.save(newRecord)
    }

    fun findByShortCode(shortCode: String): UrlMappingModel {
        return repository.findByShortCode(shortCode)
    }
}