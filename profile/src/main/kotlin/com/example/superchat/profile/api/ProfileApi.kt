package com.example.superchat.profile.api

import okhttp3.ResponseBody
import retrofit2.http.GET

interface ProfileApi {
    @GET("users/profile")
    suspend fun profile(): ResponseBody
}