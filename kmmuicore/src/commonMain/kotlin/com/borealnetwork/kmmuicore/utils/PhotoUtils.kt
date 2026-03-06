package com.borealnetwork.kmmuicore.utils

import com.borealnetwork.kmmuicore.compressImage
import kotlin.io.encoding.Base64

fun List<ByteArray>.convertByteArrayListToBase64(
    compressImage: Boolean = false,
    quality: Double = 0.7
) = map { it.convertByteArrayToBase64(compressImage, quality) }

fun ByteArray.convertByteArrayToBase64(
    compressImage: Boolean = false,
    quality: Double = 0.7
): String {
    return Base64.encode(
        if (compressImage) {
            compressImage(
                imageData = this,
                quality = quality
            )
        } else {
            this
        }
    )
}