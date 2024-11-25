package com.example.superchat.profile.model

import androidx.tracing.trace
import com.example.superchat.core.network.BuildConfig
import com.example.superchat.core.network.di.AuthApi
import com.example.superchat.profile.api.ProfileApi
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import timber.log.Timber
import javax.inject.Inject


interface Profile{
    suspend fun profile(): String
}

private const val AUTH_BASE_URL = BuildConfig.AUTH_URL

class ProfileImp @Inject constructor(
    networkJson: Json,
    @AuthApi okhttpCallFactory: dagger.Lazy<Call.Factory>,
): Profile{
    private val profileApi =  trace("ProfileAPI"){
        Retrofit.Builder()
            .baseUrl(AUTH_BASE_URL)
            .callFactory{ okhttpCallFactory.get().newCall(it)}
            .addConverterFactory(networkJson.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(ProfileApi::class.java)
    }

    override suspend fun profile(): String {
        val result = profileApi.profile().string()
        Timber.d("Get profile response: ${result}")
        return result
    }
}