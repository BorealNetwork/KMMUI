package com.borealnetwork.kmmuicore.utils

import com.borealnetwork.kmmuicore.compressImage
import kotlin.io.encoding.Base64

fun List<ByteArray>.convertByteArrayListToBase64(
    compressImage: Boolean = false,
    quality: Double = 0.7
): List<String> {
    return map { byteArray ->
        Base64.encode(
            if (compressImage) {
                compressImage(
                    imageData = byteArray,
                    quality = quality
                )
            } else {
                byteArray
            }
        )
    }
}