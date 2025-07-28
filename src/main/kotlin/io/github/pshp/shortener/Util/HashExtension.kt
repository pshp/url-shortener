package io.github.pshp.shortener.Util

import java.security.MessageDigest
import java.util.Base64

/*
SHA-256 and Base64 URL encoding
Used to create URL safe hashes
*/
fun String.sha256Base64Url(): String {
    // hash the input
    val hashBytes = MessageDigest
        .getInstance("SHA-256")
        .digest(toByteArray(Charsets.UTF_8))

    // encode with URL safe Base64
    return Base64
        .getUrlEncoder()
        .withoutPadding()
        .encodeToString(hashBytes)
}