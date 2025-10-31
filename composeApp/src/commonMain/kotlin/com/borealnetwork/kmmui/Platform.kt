package com.borealnetwork.kmmui

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform