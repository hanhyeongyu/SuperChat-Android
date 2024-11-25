package com.example.superchat.core.auth


import com.example.superchat.core.network.utils.Constants
import com.example.superchat.core.preference.PreferenceKeys
import com.example.superchat.core.preference.PreferenceStorage
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(
    private val preferenceStorage: PreferenceStorage
): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val accessToken = preferenceStorage[PreferenceKeys.Token]?.accessToken
        val request = chain.request().newBuilder()
            .addHeader(Constants.AUTHORIZATION, Constants.BEARER + " "+ accessToken)
        return chain.proceed(request.build())
    }
}