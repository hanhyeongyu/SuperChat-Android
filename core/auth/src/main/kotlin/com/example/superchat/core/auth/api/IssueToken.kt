package com.example.superchat.core.auth.api

import kotlinx.serialization.Serializable

@Serializable
data class IssueToken(
    val email: String,
    val password: String
)