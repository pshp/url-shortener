package io.github.pshp.shortener.repository

import io.github.pshp.shortener.model.UrlMappingModel
import org.springframework.data.jpa.repository.JpaRepository

interface UrlMappingRepository : JpaRepository<UrlMappingModel, Long> {
    fun findByShortCode(shortCode: String): UrlMappingModel?
    fun findByOriginalUrl(originalUrl: String): UrlMappingModel?
}