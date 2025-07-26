package io.github.pshp.shortener.repository

import io.github.pshp.shortener.model.UrlMapping
import org.springframework.data.jpa.repository.JpaRepository

interface UrlMappingRepository : JpaRepository<UrlMapping, Long> {
    fun findByEncodedUrl(encodedUrl: String): UrlMapping?
    fun findByOriginalUrl(originalUrl: String): UrlMapping?
}