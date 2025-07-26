package io.github.pshp.shortener.repository

import io.github.pshp.shortener.model.ShortUrl
import org.springframework.data.jpa.repository.JpaRepository

interface ShortUrlRepository : JpaRepository<ShortUrl, Long> {
    fun findByShortUrl(shortUrl: String) : ShortUrl
}