package io.github.pshp.shortener.dto

import jakarta.validation.constraints.NotBlank

data class EncodeUrlRequest(
    @field:NotBlank
    // TODO: add url validation check using regex
    val url: String
)