package io.github.pshp.shortener.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.Entity
import jakarta.persistence.Table
import jakarta.persistence.Id
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Column
import org.hibernate.annotations.CreationTimestamp
import java.time.Instant

@Entity
@Table(
    name="url_mapping",
)
data class UrlMappingModel(
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name = "original_url", nullable = false, unique = true)
    val originalUrl: String,

    @JsonIgnore
    @Column(name = "full_hash", nullable = false, unique = true)
    val fullHash: String,

    @Column(name = "short_code", nullable = false, unique = true)
    val shortCode: String,

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    val createdAt: Instant? = null,
)
