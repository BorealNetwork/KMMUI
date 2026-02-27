package com.borealnetwork.kmmuicore.domain.location

import kotlinx.serialization.Serializable

@Serializable
data class Location(
    val latitud: String,
    val longitud: String
){
    companion object{
        fun dummyLocation() = Location(
            latitud = "111.2232323",
            longitud = "-105.343434"
        )
    }
}