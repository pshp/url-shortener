package io.github.pshp.shortener.dto

import jakarta.validation.constraints.Pattern

data class EncodeUrlRequest(
    @field:Pattern(
        regexp = "https?://.*",
        message = "Must be a valid HTTP or HTTPS URL"
    )
    val url: String
)