package io.github.pshp.shortener.model

import jakarta.persistence.*

@Entity
@Table(
    name="url_mapping",
)
data class UrlMappingModel(
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name = "original_url", nullable = false)
    val originalUrl: String,

    @Column(name = "encoded_url", nullable = false, unique = true)
    val encodedUrl: String
)
