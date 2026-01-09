package com.borealnetwork.kmmuicore.domain.sources_files

import kotlinx.serialization.Serializable

@Serializable
sealed class PhotoSource {
    data object Camera : PhotoSource()
    data object Gallery : PhotoSource()
}