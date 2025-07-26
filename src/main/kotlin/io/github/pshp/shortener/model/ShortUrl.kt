package io.github.pshp.shortener.model

import jakarta.persistence.*

@Entity
@Table(
    name="short_urls",
)
data class ShortUrl(
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    val id: Long,

    @Column(name = "original_url", nullable = false)
    val originalUrl: String,

    @Column(name = "short_url", nullable = false, unique = true)
    val shortUrl: String
)
