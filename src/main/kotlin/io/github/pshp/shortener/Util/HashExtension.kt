package io.github.pshp.shortener.util

import java.security.MessageDigest
import java.util.Base64

/*
 SHA256 hashing and Base64 URL encoding used to create URL safe strings
 will always create identical output for the same input URL
*/
fun String.hashUrl(): String {

    // Sha256 randomises the whole URL
      val hash = MessageDigest
        .getInstance("SHA-256")
        .digest(toByteArray(Charsets.UTF_8))

    // Base64 url turns it into url safe A-z 0-9 -_ characters
    val code = Base64
        .getUrlEncoder()
        .withoutPadding()
        .encodeToString(hash)

    return code
}