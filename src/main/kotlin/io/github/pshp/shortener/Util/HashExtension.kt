package io.github.pshp.shortener.util

import java.security.MessageDigest
import java.util.Base64

// SHA256 and Base64URL encoding
// Used to create URL safe hashes
fun String.sha256Base64Url(): String {
    // hash the input
    val hash = MessageDigest
        .getInstance("SHA-256")
        .digest(toByteArray(Charsets.UTF_8))

    // encode with Base64URL
    return Base64
        .getUrlEncoder()
        .withoutPadding()
        .encodeToString(hash)
}