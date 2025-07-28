package io.github.pshp.shortener.Util

import java.security.MessageDigest
import java.util.Base64

fun String.sha256Base64Url(): String =
    Base64
        .getUrlEncoder()
        .withoutPadding()
        .encodeToString(
            MessageDigest.getInstance("SHA-256")
                .digest(toByteArray(Charsets.UTF_8))
        )