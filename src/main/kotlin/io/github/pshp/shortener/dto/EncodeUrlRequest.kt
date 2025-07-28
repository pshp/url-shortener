package io.github.pshp.shortener.dto

import jakarta.validation.constraints.Pattern

// TODO: add validation to block encoding for specific URLs, such as the endpoint of this api
data class EncodeUrlRequest(
    @field:Pattern(
        regexp = "https?://.*",
        message = "Must be a valid http or https url"
    )
    val url: String
)