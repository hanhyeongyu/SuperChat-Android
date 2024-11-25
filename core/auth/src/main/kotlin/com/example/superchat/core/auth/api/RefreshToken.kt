package com.example.superchat.core.auth.api

import kotlinx.serialization.Serializable

@Serializable
data class RefreshToken(
    val refreshToken: String
)