package io.github.pshp.shortener.util

import java.security.MessageDigest
import java.util.Base64

/*
 SHA256 and Base64 URL encoding
 used to create URL safe hashes
 note: will always create identical hashes for the same input URL
*/
fun String.hashUrl(): String {
    // hash the input using sha256
    val hash = MessageDigest
        .getInstance("SHA-256")
        .digest(toByteArray(Charsets.UTF_8))

    // encode with Base64URL
    return Base64
        .getUrlEncoder()
        .withoutPadding()
        .encodeToString(hash)
}